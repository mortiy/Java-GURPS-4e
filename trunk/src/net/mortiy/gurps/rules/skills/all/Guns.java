package net.mortiy.gurps.rules.skills.all;

import net.mortiy.gurps.rules.attributes.Attribute;
import net.mortiy.gurps.rules.skills.Skill;
import net.mortiy.gurps.rules.Character;

/**
 * Guns
 * ========================
 * Description:
 */
public class Guns extends Skill {
    public Guns(Character character, String speciality) {
        super(character, "Guns", speciality, Attribute.Dexterity, Difficulty.Easy);
    }
}
