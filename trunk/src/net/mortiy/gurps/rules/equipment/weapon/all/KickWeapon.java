package net.mortiy.gurps.rules.equipment.weapon.all;

import net.mortiy.gurps.rules.TechLevel;
import net.mortiy.gurps.rules.combat.Damage;
import net.mortiy.gurps.rules.equipment.weapon.MusclePoweredMeleeWeapon;
import net.mortiy.gurps.rules.equipment.weapon.statistics.MusclePoweredDamage;
import net.mortiy.gurps.rules.equipment.weapon.statistics.MusclePoweredMeleeWeaponMode;
import net.mortiy.gurps.rules.equipment.weapon.statistics.WeaponDamage;

/**
 * Created with IntelliJ IDEA.
 * User: oleksandr.sidko
 * Date: 09.02.13
 * Time: 20:15
 * To change this template use File | Settings | File Templates.
 */
public class KickWeapon extends MusclePoweredMeleeWeapon {
    public KickWeapon() {
        super(
                "Kick",                         // Weapon name
                new Class[]{}, // Required skill's classes
                TechLevel.Level.TL0,           // Weapon tech level
                new MusclePoweredMeleeWeaponMode[]{     // Available weapon modes
                        new MusclePoweredMeleeWeaponMode(
                                MusclePoweredDamage.Type.Thrusting,
                                new WeaponDamage(0, 0, Damage.Type.Crushing)
                        )
                },
                0,         // Cost ($)
                0,         // Weight (lbs)
                0);        // Required Strength
    }
}
