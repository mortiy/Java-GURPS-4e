package net.mortiy.gurps.rules.combat;

import java.util.Arrays;

/**
 * Your ST determines how much
 * damage you do in unarmed combat or
 * with a melee weapon.
 */
public class Damage {

    public static enum Type {
        Afflication,
        Burning,
        Corrosion,
        Crushing,
        Cutting,
        Fatigue,
        Impaling,
        SmallPiercing,
        Piercing,
        LargePiercing,
        HugePiercing,
        Toxic,
        Special
    }

    private int damageAmount;
    private Type damageType;

    public Damage(int damageAmount, Type damageType) {
        this.damageAmount = damageAmount;
        this.damageType = damageType;
    }

    public int getDamageAmount() {
        return damageAmount;
    }

    public Type getDamageType() {
        return damageType;
    }

    @Override
    public String toString() {
        return String.format("Damage: %d %s", damageAmount, (damageType == null ? null : Arrays.asList(damageType)));
    }
}
