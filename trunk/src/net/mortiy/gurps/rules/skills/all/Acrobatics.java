package net.mortiy.gurps.rules.skills.all;

import net.mortiy.gurps.rules.attributes.Attribute;
import net.mortiy.gurps.rules.skills.Skill;
import net.mortiy.gurps.rules.Character;

/**
 * Acrobatics
 * ========================
 * Description:
 */
public class Acrobatics extends Skill {
    public Acrobatics(Character character) {
        super(character, "Acrobatics", Attribute.Dexterity, Difficulty.Hard);
    }
}
