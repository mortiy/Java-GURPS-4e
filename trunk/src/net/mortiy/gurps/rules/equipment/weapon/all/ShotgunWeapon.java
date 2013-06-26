package net.mortiy.gurps.rules.equipment.weapon.all;

import net.mortiy.gurps.rules.TechLevel;
import net.mortiy.gurps.rules.equipment.weapon.FirearmRangedWeapon;
import net.mortiy.gurps.rules.equipment.weapon.statistics.WeaponMode;
import net.mortiy.gurps.rules.skills.all.Shotgun;

/**
 * Created with IntelliJ IDEA.
 * User: oleksandr.sidko
 * Date: 16.12.12
 * Time: 0:28
 * To change this template use File | Settings | File Templates.
 */
public class ShotgunWeapon extends FirearmRangedWeapon {
    public ShotgunWeapon(String name, TechLevel techLevel, WeaponMode weaponDamage, float cost, float weight) {
        super(name, techLevel, new WeaponMode[] {weaponDamage}, cost, weight);
        requiredSkills = new Class[] { Shotgun.class };
    }
}
