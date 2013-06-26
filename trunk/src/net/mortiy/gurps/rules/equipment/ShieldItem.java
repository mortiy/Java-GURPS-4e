package net.mortiy.gurps.rules.equipment;

import net.mortiy.gurps.rules.TechLevel;
import net.mortiy.gurps.rules.equipment.weapon.FirearmRangedWeapon;

/**
 * Created with IntelliJ IDEA.
 * User: oleksandr.sidko
 * Date: 29.12.12
 * Time: 2:18
 * To change this template use File | Settings | File Templates.
 */
public class ShieldItem extends Item {

    public enum Type {
        Cloak,
        Shield,
        Force
    }

    protected int defenseBonus;
    protected Type shieldType;
    protected FirearmRangedWeapon.Legality legality;

    public ShieldItem(String name, TechLevel techLevel, Type shieldType, int defenseBonus, FirearmRangedWeapon.Legality legality, float cost, float weight) {
        super(name, techLevel, cost, weight);
        this.shieldType = shieldType;
        this.defenseBonus = defenseBonus;
        this.legality = legality;
    }

    public int getDefenseBonus() {
        return defenseBonus;
    }

    public Type getShieldType() {
        return shieldType;
    }
}
