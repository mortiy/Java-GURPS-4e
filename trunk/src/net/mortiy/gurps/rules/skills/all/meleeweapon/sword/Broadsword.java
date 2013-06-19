package net.mortiy.gurps.rules.skills.all.meleeweapon.sword;

import net.mortiy.gurps.rules.Character;
import net.mortiy.gurps.rules.skills.all.meleeweapon.SwordMeleeWeapon;

/**
 * Broadsword
 * ========================
 * Description:
 * Any balanced, 2- to 4-foot blade wielded in
 * one hand – broadsword, cavalry saber,
 * scimitar, etc. This skill also covers any
 * stick or club of similar size and balance
 * to these blades, as well as bastard
 * swords, katanas, and longswords used one-handed.
 */
public class Broadsword extends SwordMeleeWeapon {

    public Broadsword(Character character) {
        super(character, "Broadsword");
        try {
            setDefault("Force Sword", -4);
            setDefault("Rapier", -4);
            setDefault("Saber", -4);
            setDefault("Shortsword", -2);
            setDefault("Two-Handed Sword", -4);
        } catch (UnknownSkillDefaultSkillException e) {
            e.printStackTrace();
        }

    }
}
