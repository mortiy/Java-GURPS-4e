package net.mortiy.gurps.rules.equipment.all;

import net.mortiy.gurps.rules.TechLevel;
import net.mortiy.gurps.rules.equipment.ShieldItem;
import net.mortiy.gurps.rules.equipment.weapon.FirearmRangedWeapon;

/**
 * Created with IntelliJ IDEA.
 * User: oleksandr.sidko
 * Date: 20.01.13
 * Time: 16:47
 * To change this template use File | Settings | File Templates.
 */
public class LargeShield extends ShieldItem {
    public LargeShield() {
        super("Large Shield", TechLevel.TL0, Type.Shield, 3, FirearmRangedWeapon.Legality.LC4, 90, 25);
    }
}
