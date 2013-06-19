package net.mortiy.gurps.rules.skills.all.meleeweapon.flail;

import net.mortiy.gurps.rules.Character;
import net.mortiy.gurps.rules.skills.Skill;
import net.mortiy.gurps.rules.skills.all.meleeweapon.FlailMeleeWeapon;

/**
 * Flail
 * ===========================
 * Any one-handed flail, such as a morningstar or nunchaku.
 */
public class Flail extends FlailMeleeWeapon {
    public Flail(Character character) {
        super(character, "Flail");
        try {
            setDefault("Axe/Mace", -4);
        } catch (Skill.UnknownSkillDefaultSkillException e) {
            e.printStackTrace();
        }
    }
}
