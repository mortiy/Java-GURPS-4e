package net.mortiy.gurps.rules.equipment.weapon.all;

import net.mortiy.gurps.rules.TechLevel;
import net.mortiy.gurps.rules.equipment.weapon.RangedWeapon;
import net.mortiy.gurps.rules.equipment.weapon.statistics.WeaponMode;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: oleksandr.sidko
 * Date: 09.02.13
 * Time: 14:45
 * To change this template use File | Settings | File Templates.
 */
public class BowWeapon extends RangedWeapon {
    public BowWeapon(String name, TechLevel techLevel, List<WeaponMode> damageTypes, float cost, float weight) {
        super(name, techLevel, damageTypes, cost, weight);
    }
}
