
package net.mortiy.gurps.rules.equipment.weapon.all;

import net.mortiy.gurps.rules.TechLevel;
import net.mortiy.gurps.rules.combat.Damage;
import net.mortiy.gurps.rules.equipment.weapon.MusclePoweredMeleeWeapon;
import net.mortiy.gurps.rules.equipment.weapon.statistics.MusclePoweredDamage;
import net.mortiy.gurps.rules.equipment.weapon.statistics.MusclePoweredMeleeWeaponMode;
import net.mortiy.gurps.rules.equipment.weapon.statistics.Parry;
import net.mortiy.gurps.rules.equipment.weapon.statistics.WeaponDamage;
import net.mortiy.gurps.rules.skills.all.meleeweapon.impact.AxeMace;

public class MaceWeapon extends MusclePoweredMeleeWeapon {

    public MaceWeapon() {
        super(
                "Mace",
                new Class[]{AxeMace.class},
                TechLevel.TL2,
                new MusclePoweredMeleeWeaponMode[]{
                        new MusclePoweredMeleeWeaponMode(
                                MusclePoweredDamage.Type.Swinging,
                                new WeaponDamage(0, 3, Damage.Type.Crushing)
                        ).setParry(0, Parry.Type.Unbalanced)
                },
                50,
                5,
                12);
    }

}
