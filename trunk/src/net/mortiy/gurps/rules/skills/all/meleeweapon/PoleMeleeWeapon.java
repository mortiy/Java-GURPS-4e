package net.mortiy.gurps.rules.skills.all.meleeweapon;

import net.mortiy.gurps.rules.*;
import net.mortiy.gurps.rules.skills.all.MeleeWeapon;

/**
 * Pole Weapon
 * =============================
 * Description:
 * Pole weapons are long (usually
 * wooden) shafts, often adorned with
 * striking heads. All require two hands.
 */
public class PoleMeleeWeapon extends MeleeWeapon {
    public PoleMeleeWeapon(Individual individual, String name) {
        super(individual, name, Difficulty.Average);
    }
}
