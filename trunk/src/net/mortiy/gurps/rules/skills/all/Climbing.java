package net.mortiy.gurps.rules.skills.all;

import net.mortiy.gurps.rules.Character;
import net.mortiy.gurps.rules.attributes.Attribute;
import net.mortiy.gurps.rules.skills.Skill;

/**
 * Climbing
 * =============================
 * Description:
 * (p. 183)
 */
public class Climbing extends Skill {

    public Climbing( Character character) {
        super(character, "Climbing", Attribute.Dexterity, Difficulty.Average);
    }
}
