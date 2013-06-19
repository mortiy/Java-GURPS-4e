package net.mortiy.gurps.rules.equipment.weapon.statistics;

/**
 * Created with IntelliJ IDEA.
 * User: oleksandr.sidko
 * Date: 08.02.13
 * Time: 21:07
 * To change this template use File | Settings | File Templates.
 */
public class WeaponMode {
    WeaponDamage weaponDamage;


    public WeaponMode(WeaponDamage weaponDamage) {
        this.weaponDamage = weaponDamage;
    }

    public WeaponDamage getWeaponDamage() {
        return weaponDamage;
    }
}
