package net.mortiy.gurps.rules.skills.all.meleeweapon.whip;

import net.mortiy.gurps.rules.*;
import net.mortiy.gurps.rules.skills.all.meleeweapon.WhipMeleeWeapon;

/**
 * Force Whip
 * =====================
 * Description:
 * Any whip made of pure energy instead of matter.
 * These are usually ultra-tech devices
 * that project energy from a powered
 * hilt, but magical or psi-tech versions
 * are possible. Most force whips can
 * lash the target but not ensnare him.
 */
public class ForceWhip extends WhipMeleeWeapon {
    public ForceWhip(Individual individual) {
        super(individual, "Force Whip", Difficulty.Average);
    }
}
