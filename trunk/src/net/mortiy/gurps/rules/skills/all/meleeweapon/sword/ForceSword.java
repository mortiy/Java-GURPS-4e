package net.mortiy.gurps.rules.skills.all.meleeweapon.sword;

import net.mortiy.gurps.rules.Character;
import net.mortiy.gurps.rules.skills.all.meleeweapon.SwordMeleeWeapon;

/**
 * ForceSword
 * ========================
 * Description:
 * Any sword with a “blade” made of energy
 * instead of matter. This generally refers
 * to an ultra-tech weapon that projects
 * energy from a powered hilt, but
 * extends to similar effects produced
 * using magic or psionics.
 */
public class ForceSword extends SwordMeleeWeapon {

    public ForceSword(Character character) {
        super(character, "Force Sword");
        try {
            setDefault("Broadsword", -3);
            setDefault("Jitte/Sai", -3);
            setDefault("Knife", -3);
            setDefault("Shortsword", -3);
            setDefault("Two-Handed Sword", -3);
        } catch (UnknownSkillDefaultSkillException e) {
            e.printStackTrace();
        }
    }
}
