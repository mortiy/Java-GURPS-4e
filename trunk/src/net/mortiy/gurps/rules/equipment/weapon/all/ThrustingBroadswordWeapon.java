package net.mortiy.gurps.rules.equipment.weapon.all;

import net.mortiy.gurps.rules.TechLevel;
import net.mortiy.gurps.rules.combat.Damage;
import net.mortiy.gurps.rules.equipment.weapon.MusclePoweredMeleeWeapon;
import net.mortiy.gurps.rules.equipment.weapon.statistics.MusclePoweredDamage;
import net.mortiy.gurps.rules.equipment.weapon.statistics.MusclePoweredMeleeWeaponMode;
import net.mortiy.gurps.rules.equipment.weapon.statistics.WeaponDamage;
import net.mortiy.gurps.rules.skills.all.meleeweapon.sword.Broadsword;

public class ThrustingBroadswordWeapon extends MusclePoweredMeleeWeapon {
    public ThrustingBroadswordWeapon() {
        super(
                "Broadsword",                         // Weapon name
                new Class[]{Broadsword.class}, // Required skill's classes
                TechLevel.TL2,           // Weapon tech level
                new MusclePoweredMeleeWeaponMode[]{     // Available damage types
                        new MusclePoweredMeleeWeaponMode(
                                MusclePoweredDamage.Type.Swinging,
                                new WeaponDamage(0, 1, Damage.Type.Cutting)
                        ),
                        new MusclePoweredMeleeWeaponMode(
                                MusclePoweredDamage.Type.Thrusting,
                                new WeaponDamage(0, 2, Damage.Type.Impaling)
                        )
                },
                600,         // Cost ($)
                3f,         // Weight (lbs)
                10);        // Required Strength
    }
}
