package net.mortiy.gurps.rules.equipment.weapon.all;

import net.mortiy.gurps.rules.TechLevel;
import net.mortiy.gurps.rules.combat.Damage;
import net.mortiy.gurps.rules.equipment.weapon.MusclePoweredMeleeWeapon;
import net.mortiy.gurps.rules.equipment.weapon.statistics.MusclePoweredMeleeWeaponMode;
import net.mortiy.gurps.rules.equipment.weapon.statistics.MusclePoweredDamage;
import net.mortiy.gurps.rules.equipment.weapon.statistics.WeaponDamage;
import net.mortiy.gurps.rules.skills.all.meleeweapon.sword.Shortsword;

/**
 * Created with IntelliJ IDEA.
 * User: oleksandr.sidko
 * Date: 29.12.12
 * Time: 1:29
 * To change this template use File | Settings | File Templates.
 */
public class ShortswordWeapon extends MusclePoweredMeleeWeapon {
    public ShortswordWeapon() {
        super(
                "Shortsword",                         // Weapon name
                new Class[]{Shortsword.class}, // Required skill's classes
                TechLevel.Level.TL2,           // Weapon tech level
                new MusclePoweredMeleeWeaponMode[]{     // Available damage types
                        new MusclePoweredMeleeWeaponMode(
                                MusclePoweredDamage.Type.Swinging,
                                new WeaponDamage(0, 0, Damage.Type.Cutting)
                        ),
                        new MusclePoweredMeleeWeaponMode(
                                MusclePoweredDamage.Type.Thrusting,
                                new WeaponDamage(0, 0, Damage.Type.Impaling)
                        )
                },
                400,         // Cost ($)
                2f,         // Weight (lbs)
                8);        // Required Strength
    }
}
