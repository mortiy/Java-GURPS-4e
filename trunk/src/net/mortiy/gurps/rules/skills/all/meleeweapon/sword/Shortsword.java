package net.mortiy.gurps.rules.skills.all.meleeweapon.sword;

import net.mortiy.gurps.rules.Individual;
import net.mortiy.gurps.rules.skills.all.meleeweapon.SwordMeleeWeapon;

/**
 * Shortsword
 * ========================
 * Description:
 * Any balanced, one-handed weapon 1-2 feet in
 * length – including the shortsword and
 * any club of comparable size and
 * balance (e.g., a police baton).
 */
public class Shortsword extends SwordMeleeWeapon {
    public Shortsword(Individual individual) {
        super(individual, "Shortsword");
        try {
            setDefault("Broadsword", -2);
            setDefault("Force Sword", -4);
            setDefault("Jitte/Sai", -3);
            setDefault("Knife", -4);
            setDefault("Saber", -4);
            setDefault("Smallsword", -4);
            setDefault("Tonfa", -3);

        } catch (UnknownSkillDefaultSkillException e) {
            e.printStackTrace();
        }
    }
}
