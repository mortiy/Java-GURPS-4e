package net.mortiy.gurps.rules.equipment.weapon.statistics;

/**
* Created with IntelliJ IDEA.
* User: oleksandr.sidko
* Date: 16.12.12
* Time: 1:48
* To change this template use File | Settings | File Templates.
*/
public class Parry {
    public static enum Type {
        /**
         * “F” means the weapon is a fencing weapon (see p. 404).
         */
        Fencing,
        /**
         * “U” means the weapon is unbalanced:
         * you cannot use it to parry if you
         * have already used it to attack this turn
         * (or vice versa).
         */
        Unbalanced,
        /**
         * “No” means the weapon cannot parry at all.
         */
        No
    }
    protected Type parryType;
    protected int modifier = 0;

    public Parry(int modifier) {
        this(modifier, null);
    }

    public Parry(int modifier, Type parryType) {
        this.modifier = modifier;
        this.parryType = parryType;
    }
}
