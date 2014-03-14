package net.mortiy.gurps.rules.skills.all.meleeweapon.impact;

import net.mortiy.gurps.rules.Individual;
import net.mortiy.gurps.rules.skills.all.meleeweapon.ImpactMeleeWeapon;

/**
 * Axe
 * ===================
 * Description:
 * Any shortor medium-length, one-handed impact
 * weapon, such as an axe, hatchet,
 * knobbed club, or pick
 */
public class AxeMace extends ImpactMeleeWeapon {
    public AxeMace(Individual individual) {
        super(individual, "Axe/Mace");
        try {
            setDefault("Flail", -4);
        } catch (UnknownSkillDefaultSkillException e) {
            e.printStackTrace();
        }
    }
}
