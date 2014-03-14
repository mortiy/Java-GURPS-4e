package net.mortiy.gurps.rules.combat;

import net.mortiy.gurps.Log;
import net.mortiy.gurps.rules.Individual;
import net.mortiy.gurps.rules.attributes.Attribute;
import net.mortiy.gurps.rules.individual.Body;
import net.mortiy.gurps.rules.combat.maneuver.AllOutDefenseManeuver;
import net.mortiy.gurps.rules.combat.maneuver.WaitManeuver;
import net.mortiy.gurps.rules.equipment.TwoHanded;
import net.mortiy.gurps.rules.equipment.all.LargeShield;
import net.mortiy.gurps.rules.equipment.weapon.Weapon;
import net.mortiy.gurps.rules.map.GameMap;
import org.newdawn.slick.Image;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents Character who takes part in combat
 */
public class Fighter implements GameMap.MapToken {
    private Individual individual;
    private Maneuver nextManeuver;
    private Defense defense;
    private boolean isActive = true;
    private int usedMoves = 0;

    private List<Preparable> readyList = new ArrayList<>();

    public Fighter(Individual individual) {
        this.individual = individual;
        this.defense = new Defense(this);
    }

    public Individual getIndividual() {
        return individual;
    }

    public void setNextManeuver(Maneuver maneuver) {
        this.nextManeuver = maneuver;
    }

    public Maneuver getNextManeuver() {
        return nextManeuver;
    }

    public Defense getDefense() {
        return defense;
    }

    public List<Preparable> getReadyList(){
        return readyList;
    }

    public boolean hasManeuver() {
        return nextManeuver != null;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public void resetMoves(){
        usedMoves = 0;
    }

    public int getAvailableMoves(){
        return getAvailableMovesForManeuver(nextManeuver);
    }

    private int getAvailableMovesForManeuver(Maneuver maneuver){
        int totalMoves = 0;
        int basicMove = (int) individual.getBasicMove().getValue();
        float basicStep = basicMove / 10f;
        int fighterStep = (basicStep < 1) ? 1 : (int) Math.floor(basicStep);

        switch (maneuver.getType()){
            case DoNothing:
                totalMoves = 0;
                break;
            case Move:
                totalMoves = basicMove;
                break;
            case ChangePosture:
                totalMoves = 0;
                break;
            case Aim:
                // If character has equiped two handed or braced weapon than he can't step:
                if(individual.getEquipment().getItemsByType(TwoHanded.class).size() > 0){
                    totalMoves = 0;
                } else {
                    totalMoves = fighterStep;
                }
                break;
            case Concentrate:
            case Evaluate:
            case Feint:
            case Attack:
            case Ready:
                totalMoves = fighterStep;
                break;
            case AllOutDefense:

                totalMoves = fighterStep;
                AllOutDefenseManeuver defenseManeuver = (AllOutDefenseManeuver) maneuver;

                if (defenseManeuver.getDefenseType() == AllOutDefenseManeuver.Type.IncreasedDefense){
                    if(defenseManeuver.getDefenseStrategy() == Defense.Strategy.Dodge){
                        totalMoves = (int) Math.floor(basicMove / 2f);
                    }
                }
                break;

            case AllOutAttack:
                // Only forward move
                totalMoves = (int) Math.floor(basicMove / 2f);
                break;
            case MoveAndAttack:
                // TODO: -2 on any rolls the GM required to avoid falling, tripping over obstacles, etc.
                totalMoves = basicMove;
                break;
            case Wait:
                //TODO: Wait manuever (p. 366)
                WaitManeuver waitManeuver = (WaitManeuver) maneuver;
                totalMoves = getAvailableMovesForManeuver(waitManeuver.getNextManeuver());
                break;

        }
        return totalMoves - usedMoves;
    }

    public int getAttackModifier(ManeuverType maneuverType, Weapon weapon) {

        // TODO: Add more attack modifiers

        int attackModifiers = 0;
        // Move and Attack: -4
        if(maneuverType == ManeuverType.MoveAndAttack){
            attackModifiers -= 4;
        }
        // All-Out Attack (Determined): +4
        else if(maneuverType == ManeuverType.AllOutAttack){
            attackModifiers += 4;
        }
        // Holding a large shield: -2
        if(individual.getEquipment().hasEquipped(LargeShield.class)){
            attackModifiers -= 2;
        }

        // ST below that required for weapon: -1 per point of deficit
        int strengthDeficit = weapon.getMinStrength() - individual.getBasicAttribute(Attribute.Strength).getLevel();
        if(strengthDeficit > 0){
            attackModifiers -= strengthDeficit;
        }

        return attackModifiers;
    }


    /**
     * Any damage left over after subtracting DR from basic damage is “penetrating damage.”
     If there is any penetrating damage, multiply it by the attack’s “wounding modifier.”
     This is a multiplier that depends on damage type:
     • Small piercing (pi-): x0.5.
     • Cutting (cut) and large piercing (pi+): x1.5.
     • Impaling (imp): x2.
     The damage after this multiplier determines the injury: the HP lost by the target.
     Round fractions down, but the minimum injury is 1 HP for any attack that penetrates
     DR at all. Reduce the victim’s current HP total by the injury sustained.
     * @param damage
     */
    public void injure(Body.Part bodyPart, Damage damage) {
        // TODO: Use "armor divisor"
        // TODO: Improve target fighter DR

        // Calc penetrating damage:
        int damageResistance = individual.getEquipment().getResistance(bodyPart, damage.getDamageType());

        Log.i("Fighter", "'%s' got %s", getIndividual().getName(), damage);

        if(damageResistance > 0){
            Log.i("Fighter", "'%s' armor absorbs %d damage", getIndividual().getName(), damageResistance);
        }

        int penetratingDamage = damage.getDamageAmount() - damageResistance;

        // Calc wounding modifier:
        float woundingMultiplier = 1.0f;
        switch(damage.getDamageType()){
            case SmallPiercing:
                woundingMultiplier = 0.5f;
                break;
            case LargePiercing:
            case Cutting:
                woundingMultiplier = 1.5f;
                break;
            case Impaling:
                woundingMultiplier = 2.0f;
                break;
        }

        if(woundingMultiplier != 1.0f){
            Log.i("Fighter", "%s damage is multiplied by %.1f", damage.getDamageType(), woundingMultiplier);

        }

        // Injury:
        int injury = (int) Math.floor(penetratingDamage * woundingMultiplier);
        int actualInjury = injury < 1 ? 1 : injury;

        individual.injure(actualInjury);
        Log.i("Fighter", "'%s' injured by %d and has %.1f HP now", getIndividual().getName(), actualInjury, getIndividual().getHitpoints().getCurrentValue());
    }

    public Weapon getActiveWeapon() {
        return (Weapon) individual.getEquipment().getEquipedItem(Body.Part.RightHand);
    }


    @Override
    public Image getImage() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String toString() {
        return String.format("Fighter '%s'", individual.getName());
    }
}
