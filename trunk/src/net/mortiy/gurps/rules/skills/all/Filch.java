package net.mortiy.gurps.rules.skills.all;

import net.mortiy.gurps.rules.attributes.Attribute;
import net.mortiy.gurps.rules.skills.Skill;
import net.mortiy.gurps.rules.Character;

/**
 * Filch
 * ========================
 * Description:
 */
public class Filch extends Skill {
    public Filch(Character character) {
        super(character, "Filch", Attribute.Dexterity, Difficulty.Average);
    }
}
