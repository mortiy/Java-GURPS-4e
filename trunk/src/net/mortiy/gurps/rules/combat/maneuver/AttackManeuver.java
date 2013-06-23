package net.mortiy.gurps.rules.combat.maneuver;

import net.mortiy.gurps.rules.combat.Fighter;
import net.mortiy.gurps.rules.combat.Maneuver;
import net.mortiy.gurps.rules.combat.ManeuverType;
import net.mortiy.gurps.rules.equipment.weapon.Weapon;

/**
 * Created with IntelliJ IDEA.
 * User: oleksandr.sidko
 * Date: 20.01.13
 * Time: 16:58
 * To change this template use File | Settings | File Templates.
 */
public class AttackManeuver extends Maneuver {
    private Fighter targetFigher;
    private Weapon weapon;
    private Type attackType;

    public AttackManeuver(Fighter targetFighter, Type attackType, Weapon weapon) {
        super(ManeuverType.Attack);
        this.targetFigher = targetFighter;
        this.attackType = attackType;
        this.weapon = weapon;

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
}
