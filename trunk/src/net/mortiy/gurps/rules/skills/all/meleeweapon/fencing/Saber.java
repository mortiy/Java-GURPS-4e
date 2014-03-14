package net.mortiy.gurps.rules.skills.all.meleeweapon.fencing;

import net.mortiy.gurps.rules.Individual;
import net.mortiy.gurps.rules.skills.all.meleeweapon.FencingMeleeWeapon;

/**
 * Saber
 * ========================
 * Description:
 * Any light cutand-
 * thrust sword. Note that cavalry
 * sabers are quite heavy, and use
 * Broadsword instead.
 */
public class Saber extends FencingMeleeWeapon {

    public Saber(Individual individual) {
        super(individual, "Saber");
        try {
            setDefault("Broadsword", -4);
            setDefault("Shortsword", -4);
        } catch (UnknownSkillDefaultSkillException e) {
            e.printStackTrace();
        }
    }
}
