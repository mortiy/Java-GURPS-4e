package net.mortiy.gurps.rules.combat;


import net.mortiy.gurps.rules.Individual;
import net.mortiy.gurps.rules.equipment.ShieldItem;
import net.mortiy.gurps.rules.equipment.weapon.MusclePoweredMeleeWeapon;
import net.mortiy.gurps.rules.equipment.weapon.Weapon;
import net.mortiy.gurps.rules.equipment.weapon.all.KnifeWeapon;
import net.mortiy.gurps.rules.skills.Skill;
import net.mortiy.gurps.rules.skills.all.meleeweapon.PoleMeleeWeapon;
import net.mortiy.gurps.rules.skills.all.meleeweapon.WhipMeleeWeapon;
import net.mortiy.gurps.rules.skills.all.meleeweapon.whip.Kusari;

//import net.mortiy.gurps.rules.equipment.weapon.all.KusariWeapon;

/**
 * Created with IntelliJ IDEA.
 * User: oleksandr.sidko
 * Date: 26.12.12
 * Time: 0:32
 * To change this template use File | Settings | File Templates.
 */
public class Defense {
    private Fighter fighter;
    private Strategy bestStrategy;
    private static Strategy strategies[] = Strategy.values();

    public Defense(Fighter fighter) {
        this.fighter = fighter;
        determineBestStrategy();
    }

    private void determineBestStrategy() {
        int bestStrategyLevel = 0;
        for (Strategy strategy : Strategy.values()) {
            int strategyLevel = getDefenseStrategyLevel(strategy);
            //Log.i("Defense", String.format("'%s': %s = %d", fighter.getCharacter().getName(), strategy, strategyLevel));
            if (strategyLevel > bestStrategyLevel) {
                bestStrategyLevel = strategyLevel;
                bestStrategy = strategy;

            }
        }
    }

    public static Strategy getDefenseStrategy(int strategyIndex) {
        return strategies[strategyIndex];
    }

    public Strategy getBestStrategy() {
        return bestStrategy;
    }


    public int getBestStrategyLevel() {
        return getDefenseStrategyLevel(bestStrategy);
    }

    public int getDefenseStrategyLevel(Strategy strategy) {
        Individual individual = fighter.getIndividual();
        int strategyLevel = 0;
        switch (strategy) {
            case Dodge:
                // TODO: Sacrificial, Acrobatic, Vehicular dodges
                strategyLevel = (int) individual.getDodge().getValue();
                break;

            case Block:

                int blockSkillLevel = 0;
                if (individual.hasLearntSkill("Shield (Shield)")) {
                    int shieldSkillLevel = individual.getSkill("Shield (Shield)").getLevel();
                    blockSkillLevel = shieldSkillLevel;
                }
                if (individual.hasLearntSkill("Cloak")) {
                    int cloakSkillLevel = individual.getSkill("Cloak").getLevel();
                    if (cloakSkillLevel > blockSkillLevel) {
                        blockSkillLevel = cloakSkillLevel;
                    }
                }
                strategyLevel += (int) Math.floor(blockSkillLevel / 2f + 3);

                if(individual.getEquipment().hasEquipped(ShieldItem.class)){
                    strategyLevel += 1;
                }

                break;

            case Parry:
                Weapon item = fighter.getActiveWeapon();
                int parryLevel = 0;
                int parryModifier = 0;
                if (item instanceof MusclePoweredMeleeWeapon) {

                    MusclePoweredMeleeWeapon weapon = (MusclePoweredMeleeWeapon) item;
                    Class weaponSkillClass = weapon.getRequiredSkillClass();

                    // Knives give -1 modifier to parry:
                    if (weapon instanceof KnifeWeapon) {
                        parryModifier -= 1;
                    }
                    // Whip or Kusary are -2
                    else if (weaponSkillClass.isAssignableFrom(WhipMeleeWeapon.class)
                          || weaponSkillClass.isAssignableFrom(Kusari.class)) {
                        parryModifier -= 2;
                    }
                    // Quarterstaff gives +2
                    else if (weaponSkillClass.isAssignableFrom(PoleMeleeWeapon.class) ) {
                        parryModifier += 2;
                    }

                    Skill weaponSkill = individual.getSkill(weaponSkillClass);
                    if (weaponSkill.getRawLevel() > 0) {
                        parryLevel = (int) Math.floor(3 + weaponSkill.getLevel() / 2f);
                    }

                    strategyLevel = parryLevel + parryModifier;
                } else {
                    strategyLevel = 0;
                }
                break;

        }
        return strategyLevel;
    }

    public int getDefenseLevel() {
        return getBestStrategyLevel();
    }

    public enum Strategy {
        /**
         * A “dodge” is an active attempt to
         * move out of the perceived path of an
         * attack. This is often the best defense
         * when you’re not skilled with your
         * weapon and you have no shield, when
         * you’re attacked multiple times, or
         * when your foe has such a powerful
         * weapon that you fear parrying or
         * blocking it may destroy your weapon
         * or shield.
         */
        Dodge,
        /**
         * A “parry” is an attempt to deflect a
         * blow using a weapon or your bare
         * hands. You cannot parry unless your
         * weapon is ready – or, if you are
         * unarmed, you have an empty hand.
         */
        Parry,
        /**
         * A “block” is an attempt to interpose
         * a shield, cloak, or similar large object
         * between yourself and an attack. This
         * requires a ready shield or cloak. (If
         * you’re strong enough to grab and lift
         * someone, you can block with his
         * body!)
         */
        Block
    }
}
