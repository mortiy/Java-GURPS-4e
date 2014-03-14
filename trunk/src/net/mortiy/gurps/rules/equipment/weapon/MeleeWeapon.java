package net.mortiy.gurps.rules.equipment.weapon;

import net.mortiy.gurps.Log;
import net.mortiy.gurps.rules.Individual;
import net.mortiy.gurps.rules.TechLevel;
import net.mortiy.gurps.rules.equipment.weapon.statistics.MusclePoweredMeleeWeaponMode;
import net.mortiy.gurps.rules.equipment.weapon.statistics.MusclePoweredDamage;
import net.mortiy.gurps.rules.equipment.weapon.statistics.WeaponMode;
import net.mortiy.gurps.rules.table.RollFormula;
import sun.plugin.dom.exception.InvalidStateException;

import java.util.List;

/**
 * Represent weapons that powered with characters strength.
 */
public class MeleeWeapon extends Weapon {

    public MeleeWeapon(String name, TechLevel techLevel, List<? extends WeaponMode> weaponModes, float cost, float weight) {
        super(name, techLevel, weaponModes, cost, weight);
    }

    public RollFormula getDamageFormula(Individual individual) {
        try {
            return getDamageFormula(individual, ((MusclePoweredMeleeWeaponMode) activeWeaponMode).getBasicType());
        } catch (WeaponHasNoSuchDamageTypeException e) {
            String errorMessage = "Weapon was set active damage type which doesn't belongs to it.";
            Log.e("Weapon", errorMessage);
            throw new InvalidStateException(errorMessage);
        }

    }

    public RollFormula getDamageFormula(Individual individual, MusclePoweredDamage.Type damageType) throws WeaponHasNoSuchDamageTypeException {
        MusclePoweredMeleeWeaponMode musclePoweredDamage = null;
        List<MusclePoweredMeleeWeaponMode> damageTypes = (List<MusclePoweredMeleeWeaponMode>) getWeaponModes();
        for (MusclePoweredMeleeWeaponMode mpd : damageTypes) {
            if (mpd.getBasicType() == damageType) {
                musclePoweredDamage = mpd;
                break;
            }
        }
        if (musclePoweredDamage == null) {
            Log.w("Fighter", String.format("'%s' can't '%s' with '%s' weapon", individual.getName(), damageType, getName()));
            throw new WeaponHasNoSuchDamageTypeException();
        }

        RollFormula basicFormula = MusclePoweredDamage.getDamageFormula(individual, damageType);
        RollFormula weaponPowerFormula = new RollFormula(
                basicFormula.getDiceQuantity(),
                basicFormula.getModifier() + musclePoweredDamage.getWeaponDamage().getDamageFormula().getModifier()
        );

        return weaponPowerFormula;
    }

    @Override
    public MusclePoweredMeleeWeaponMode getActiveWeaponMode() {
        return (MusclePoweredMeleeWeaponMode) super.getActiveWeaponMode();    //To change body of overridden methods use File | Settings | File Templates.
    }

    public class WeaponHasNoSuchDamageTypeException extends Throwable {
    }
}
