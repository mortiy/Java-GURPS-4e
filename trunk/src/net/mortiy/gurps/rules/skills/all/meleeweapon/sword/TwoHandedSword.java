package net.mortiy.gurps.rules.skills.all.meleeweapon.sword;

import net.mortiy.gurps.rules.Individual;
import net.mortiy.gurps.rules.skills.all.meleeweapon.SwordMeleeWeapon;

/**
 * Two-Handed Sword
 * ========================
 * Description:
 * Any balanced, two-handed blade over 4
 * feet in length: greatswords, zweihanders,
 * etc. This skill also covers
 * quarterstaffs wielded like swords, as
 * well as bastard swords, katanas, and
 * longswords used two-handed
 */
public class TwoHandedSword extends SwordMeleeWeapon {

    public TwoHandedSword(Individual individual) {
        super(individual, "Two-Handed Sword");
        try {
            setDefault("Force Sword", -4);
            setDefault("Broadsword", -4);
        } catch (UnknownSkillDefaultSkillException e) {
            e.printStackTrace();
        }

    }
}
