package net.mortiy.gurps.rules.equipment.weapon;

import net.mortiy.gurps.rules.TechLevel;
import net.mortiy.gurps.rules.equipment.weapon.statistics.WeaponMode;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: oleksandr.sidko
 * Date: 16.12.12
 * Time: 1:15
 * To change this template use File | Settings | File Templates.
 */
public class RangedWeapon extends Weapon {
    /**
     * Add Accuracy to your skill if you took an
     Aim maneuver on the turn prior to your attack.
     If the weapon has a builtin scope, the bonus for
     this appears as a separate modifier after
     the weapon’s base Acc; e.g., “7+2.”
     */
    protected int accuracy;
    /**
     * If a weapon has only one range number, this is the
     Maximum Range (Max) in yards at
     which it can attack a target.
     */
    protected Range range;
    /**
     * The maximum number of shots an ordinary
     shooter can fire in a one-second turn.
     */
    protected RateOfFire rateOfFire;
    /**
     * The number of shots the weapon can fire before
     you must reload or recharge it.
     */
    protected int shots;
    /**
     * The parenthetical number following
     Shots indicates the number of onesecond
     Ready maneuvers needed to
     reload all of the weapon’s shots
     */
    protected int reloadTime;
    /**
     *  “T” means the weapon is thrown.
     To “reload,” pick it up or ready a new weapon!
     */
    protected boolean isThrown;

    protected  int bulk;

    public RangedWeapon(String name, TechLevel.Level techLevel, List<WeaponMode> damageTypes, float cost, float weight) {
        super(name, techLevel, damageTypes, cost, weight);
    }


    public int getAccuracy() {
        return accuracy;
    }

    public Range getRange() {
        return range;
    }



    private class RateOfFire {
    }

    private class Range {
    }
}
