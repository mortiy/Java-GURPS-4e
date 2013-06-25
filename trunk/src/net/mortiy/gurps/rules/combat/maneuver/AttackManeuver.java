package net.mortiy.gurps.rules.combat.maneuver;

import net.mortiy.gurps.Log;
import net.mortiy.gurps.rules.Character;
import net.mortiy.gurps.rules.character.Body;
import net.mortiy.gurps.rules.combat.*;
import net.mortiy.gurps.rules.combat.exceptions.ImpossibleManeuverException;
import net.mortiy.gurps.rules.combat.exceptions.IsNotReadyException;
import net.mortiy.gurps.rules.equipment.Item;
import net.mortiy.gurps.rules.equipment.weapon.MeleeWeapon;
import net.mortiy.gurps.rules.equipment.weapon.MusclePoweredMeleeWeapon;
import net.mortiy.gurps.rules.equipment.weapon.Weapon;
import net.mortiy.gurps.rules.equipment.weapon.statistics.MusclePoweredDamage;
import net.mortiy.gurps.rules.skills.Skill;
import net.mortiy.gurps.rules.table.DiceRoller;
import net.mortiy.gurps.rules.table.RollFormula;
import net.mortiy.gurps.rules.table.rolls.SuccessRoll;

public class AttackManeuver extends Maneuver implements ManeuverResolver {
    private Fighter targetFigher;
    private Weapon weapon;
    private Type attackType;
    private Body.Part bodyPart;


    public AttackManeuver(Fighter targetFighter, Type attackType, Weapon weapon) {
        this(targetFighter, attackType, weapon, Body.Part.Torso);
    }

    public AttackManeuver(Fighter targetFighter, Type attackType, Weapon weapon, Body.Part bodyPart) {
        super(ManeuverType.Attack);
        this.targetFigher = targetFighter;
        this.attackType = attackType;
        this.weapon = weapon;
        this.bodyPart = bodyPart;
    }

    @Override
    public ManeuverResult resolve(Fighter fighter) throws ImpossibleManeuverException, IsNotReadyException {
        Character fighterCharacter = fighter.getCharacter();
        Fighter targetFighter = getTargetFigher();

        if(fighter.equals(targetFighter)){
            Log.i("Attack Maneuver", "%s attacks himself!", fighterCharacter.getName());

        }

        Item weapon = fighter.getActiveWeapon();
        if (weapon == null) {
            throw new ImpossibleManeuverException("'%s' has no equipped weapon.", fighterCharacter.getName());
        }
        if (!fighter.getReadyList().contains(weapon)) {
            throw new IsNotReadyException();
        }
        // Check if equipped item can be used as weapon:
        if (weapon instanceof Weapon) {

            // If weapon is Muscle Powered:
            if (weapon instanceof MeleeWeapon) {

                Damage fightersDamage = damageRoll(fighter);
                // Perform ATTACK ROLL:
                switch (attackRoll(fighter)) {
                    case CriticalSuccess:
                        // Critical hit can't be defended,
                        // so no defence roll by target fighter performed:
                        targetFighter.injure(bodyPart, fightersDamage);
                        return new ManeuverResult(ManeuverResult.Status.Success);
                    case Success:

                        // Perform DEFENSE ROLL:
                        switch (defenseRoll(targetFighter)) {
                            case CriticalSuccess:
                            case Success:
                                // TODO: Successful defense event

                                break;
                            case CriticalFailure:
                            case Failure:
                                targetFighter.injure(bodyPart, fightersDamage);
                                break;
                        }
                        break;
                    case CriticalFailure:
                    case Failure:
                        // TODO: Failed attack event
                        break;
                }
            }
        } else {
            throw new ImpossibleManeuverException("'%s' is not weapon.", weapon.getName());
        }
        return new ManeuverResult(ManeuverResult.Status.Failure);
    }


    /**
     * Returns damage amount dealt by fighter with given weapon
     *
     * @param fighter Attacking fighter
     * @return Damage object
     * @throws ImpossibleManeuverException
     */
    private Damage damageRoll(Fighter fighter) throws ImpossibleManeuverException {

        Weapon weapon = fighter.getActiveWeapon();
        Character fighterCharacter = fighter.getCharacter();
        String fighterName = fighterCharacter.getName();

        int basicDamage;
        RollFormula damageFormula;
        Damage.Type damageType = null;
        if (weapon instanceof MusclePoweredMeleeWeapon) {
            MusclePoweredMeleeWeapon meeleeWeapon = (MusclePoweredMeleeWeapon) weapon;
            MusclePoweredDamage.Type physicalDamageType = meeleeWeapon.getActiveWeaponMode().getBasicType();
            damageType = meeleeWeapon.getActiveWeaponMode().getWeaponDamage().getDamageType();
            try {
                damageFormula = meeleeWeapon.getDamageFormula(fighterCharacter, physicalDamageType);
            } catch (MeleeWeapon.WeaponHasNoSuchDamageTypeException e) {
                throw new ImpossibleManeuverException("'%s' has no '%s' damage type.", fighterName, physicalDamageType);
            }
        } else {
            // Mock:
            damageFormula = new RollFormula(1, 2);

        }
        basicDamage = DiceRoller.getInstance().roll(damageFormula).getTotal();

        // TODO: Add damage modifiers:

        _log("'%s' performs damage roll %s = [%d]", fighterName, damageFormula, basicDamage);

        return new Damage(basicDamage, damageType);
    }

    public DiceRoller.RollResult attackRoll(Fighter fighter) {
        Character fighterCharacter = fighter.getCharacter();
        String fighterName = fighterCharacter.getName();
        // Calculate Attack roll modifierse:
        // Get characters skill level for used weapon:
        Weapon weapon = fighter.getActiveWeapon();
        Class weaponSkillClass = weapon.getRequiredSkillClass();
        Skill weaponSkill = fighter.getCharacter().getSkill(weaponSkillClass);
        Integer skillLevel = weaponSkill.getSkillLevel();

        _log("'%s' has weapon skill '%s' level at %d", fighterName, weaponSkill.getName(), skillLevel);

        int attackModifiers = fighter.getAttackModifier(fighter.getActiveManeuver().getType(), weapon);

        SuccessRoll attackSuccessRoll = weaponSkill.successRoll(attackModifiers);

        // region Attack logging
        switch (attackSuccessRoll.getRollResult()) {
            case CriticalSuccess:
                _log("'%s' made critical hit with roll [%d] against [%d]", fighterName, attackSuccessRoll.getDiceRoll(), skillLevel);

                break;
            case Success:
                _log("'%s' makes hit with roll [%d] against [%d]", fighterName, attackSuccessRoll.getDiceRoll(), skillLevel);
                break;
            case CriticalFailure:
            case Failure:
                // TODO: Failed attack event
                _log("'%s' has failed attack with roll [%d] against [%d]", fighterName, attackSuccessRoll.getDiceRoll(), skillLevel);
                break;
        }

        // endregion

        return attackSuccessRoll.getRollResult();

    }

    public DiceRoller.RollResult defenseRoll(Fighter targetFighter) {
        // Resolve hit success:
        // Target fighter tries to perform defend roll:
        // TODO: Defence Roll

        Maneuver activeManeuver = targetFighter.getActiveManeuver();
        String targetFighterName = targetFighter.getCharacter().getName();

        // Defense is unavailable for fighter who already performed AllOutAttack this turn:
        if (activeManeuver.getType() == ManeuverType.AllOutAttack) {
            _log("'%s' performed earlier AllOutAttack so can not defended", targetFighterName);
            return DiceRoller.RollResult.Failure;
        }

        Defense defense = targetFighter.getDefense();
        _log("'%s' tries to defend with <%s>", targetFighterName, defense.getBestStrategy());
        int defenseLevel = defense.getDefenseLevel();
        SuccessRoll defenseRoll = new SuccessRoll(defenseLevel).roll();
        DiceRoller.RollResult rollResult = defenseRoll.getRollResult();

        // region Defense logging
        switch (rollResult) {
            case CriticalSuccess:
            case Success:
                _log("'%s' successfully defended with <%s>", targetFighterName, defense.getBestStrategy());
                break;
            case CriticalFailure:
            case Failure:
                _log("'%s' failed <%s>", targetFighterName, defense.getBestStrategy());
                break;
        }
        // endregion

        return rollResult;
    }


    public enum Type {
        /**
         * When you take a maneuver that lets you
         * make a melee attack, you must specify who
         * you are attacking, and with what weapon.
         * You can make a melee attack using any
         * ready melee weapon (including a natural
         * weapon such as a kick, bite, or punch).
         */
        Melee,
        /**
         * A “ranged attack” is any attack with a
         * weapon used at a distance, from a thrown
         * rock to a laser rifle.
         */
        Ranged

    }

    public Weapon getWeapon() {
        return weapon;
    }

    public boolean hasTarget() {
        return targetFigher != null;
    }

    public Fighter getTargetFigher() {
        return targetFigher;
    }

    private void _log(String message, Object... args) {
        Log.i("Attack Maneuver", message, args);
    }
}
