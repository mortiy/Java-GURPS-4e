package net.mortiy.gurps.rules.skills.all.meleeweapon.flail;

import net.mortiy.gurps.rules.Individual;
import net.mortiy.gurps.rules.skills.Skill;
import net.mortiy.gurps.rules.skills.all.meleeweapon.FlailMeleeWeapon;

/**
 * Flail
 * ===========================
 * Any one-handed flail, such as a morningstar or nunchaku.
 */
public class Flail extends FlailMeleeWeapon {
    public Flail(Individual individual) {
        super(individual, "Flail");
        try {
            setDefault("Axe/Mace", -4);
        } catch (Skill.UnknownSkillDefaultSkillException e) {
            e.printStackTrace();
        }
    }
}
