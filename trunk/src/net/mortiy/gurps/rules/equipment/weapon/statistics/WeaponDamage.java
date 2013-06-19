package net.mortiy.gurps.rules.equipment.weapon.statistics;

import net.mortiy.gurps.rules.combat.Damage;
import net.mortiy.gurps.rules.table.RollFormula;

/**
* Created with IntelliJ IDEA.
* User: oleksandr.sidko
* Date: 16.12.12
* Time: 1:45
* To change this template use File | Settings | File Templates.
*/
public class WeaponDamage {

    protected RollFormula damageFormula;
    protected Damage.Type damageType;
    /**
     * Armor Divisors: A parenthetical
     * number after damage – e.g., (2) – is an
     * armor divisor. A fractional divisor
     * increases DR: (0.5) multiplies DR by 2;
     * (0.2) multiplies it by 5; and (0.1) multiplies
     * it by 10.
     */
    protected float armorDivisor = 0f;

    public WeaponDamage(int diceQuantity, int modifier, Damage.Type damageType) {
        this(diceQuantity, modifier, damageType, 1);
    }

    public WeaponDamage(int diceQuantity, int modifier, Damage.Type damageType, float armorDivisor) {
        this.damageFormula = new RollFormula(diceQuantity, modifier);
        this.damageType = damageType;
        this.armorDivisor = armorDivisor;
    }

    public RollFormula getDamageFormula() {
        return damageFormula;
    }

    public Damage.Type getDamageType() {
        return damageType;
    }

    public float getArmorDivisor() {
        return armorDivisor;
    }
}
