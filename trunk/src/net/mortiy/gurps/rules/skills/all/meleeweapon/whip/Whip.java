package net.mortiy.gurps.rules.skills.all.meleeweapon.whip;

import net.mortiy.gurps.rules.*;
import net.mortiy.gurps.rules.skills.all.meleeweapon.WhipMeleeWeapon;

/**
 * Whip
 * ==================
 * Description:
 * Any ordinary whip.
 */
public class Whip extends WhipMeleeWeapon {
    public Whip(Individual individual) {
        super(individual, "Whip", Difficulty.Average);
    }
}
