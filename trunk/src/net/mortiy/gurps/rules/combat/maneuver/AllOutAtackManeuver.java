package net.mortiy.gurps.rules.combat.maneuver;

import net.mortiy.gurps.rules.combat.Fighter;
import net.mortiy.gurps.rules.combat.ManeuverType;
import net.mortiy.gurps.rules.equipment.weapon.Weapon;

/**
 * Created with IntelliJ IDEA.
 * User: oleksandr.sidko
 * Date: 10.02.13
 * Time: 18:36
 * To change this template use File | Settings | File Templates.
 */
public class AllOutAtackManeuver extends AttackManeuver {
    public interface AllOutAtackOption {

    };
    public enum MeleeOption implements AllOutAtackOption {
        /**
         * Make a single attack at +4 to hit.
         */
        Determined,
        /**
        * Make two attacks against
        * the same foe, if you have two ready
        * weapons or one weapon that does
        * not have to be readied after use.
        * Attacks with a second weapon held
        * in the off hand are at the usual -4.
        */
        Double,
        /**
         * Make one Feint (see below)
         * and then one attack against the same foe.
         */
        Feint,
        /**
         * Make a single attack, at normal skill. If you hit, you get +2 to
         * damage – or +1 damage per die, if that would be better. This only
         * applies to melee attacks doing ST-based thrust or swing damage, not
         * to weapons such as force swords.
         */
        Strong

    }

    public enum RangedOption implements AllOutAtackOption {
        /**
         * Make a single attack at +1 to hit.
         */
        Determined,
        /**
         * Take your entire turn to spray an area with automatic fire.
         * Your weapon must have RoF 5+.
         * Ask the GM for details or see Suppression Fire (p. 410).
         */
        SeppresionFire
    }

    AllOutAtackOption option;

    public AllOutAtackManeuver(Fighter targetFighter, AttackManeuver.Type attackType, Weapon weapon, AllOutAtackOption option) {
        super(targetFighter, attackType, weapon);
        this.type = ManeuverType.AllOutAttack;
        this.option = option;
    }
}
