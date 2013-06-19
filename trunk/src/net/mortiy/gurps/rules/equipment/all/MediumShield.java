package net.mortiy.gurps.rules.equipment.all;

import net.mortiy.gurps.rules.TechLevel;
import net.mortiy.gurps.rules.equipment.ShieldItem;
import net.mortiy.gurps.rules.equipment.weapon.FirearmRangedWeapon;

/**
 * Created with IntelliJ IDEA.
 * User: oleksandr.sidko
 * Date: 20.01.13
 * Time: 16:48
 * To change this template use File | Settings | File Templates.
 */
public class MediumShield extends ShieldItem {
    public MediumShield() {
        super("Medium Shield", TechLevel.Level.TL0, Type.Shield, 2, FirearmRangedWeapon.Legality.LC4, 60, 15);
    }
}
