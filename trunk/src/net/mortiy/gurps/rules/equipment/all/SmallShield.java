package net.mortiy.gurps.rules.equipment.all;

import net.mortiy.gurps.rules.TechLevel;
import net.mortiy.gurps.rules.equipment.ShieldItem;
import net.mortiy.gurps.rules.equipment.weapon.FirearmRangedWeapon;

/**
 * Created with IntelliJ IDEA.
 * User: oleksandr.sidko
 * Date: 29.12.12
 * Time: 2:18
 * To change this template use File | Settings | File Templates.
 */
public class SmallShield extends ShieldItem {
    public SmallShield() {
        super("Small Shield", TechLevel.TL0, Type.Shield, 1, FirearmRangedWeapon.Legality.LC4, 40, 8);
    }
}
