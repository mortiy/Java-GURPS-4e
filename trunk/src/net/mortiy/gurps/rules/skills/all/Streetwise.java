package net.mortiy.gurps.rules.skills.all;

import net.mortiy.gurps.rules.attributes.Attribute;
import net.mortiy.gurps.rules.skills.Skill;
import net.mortiy.gurps.rules.Character;

/**
 * Streetwise
 * ========================
 * Description:
 */
public class Streetwise extends Skill {
    public Streetwise(Character character) {
        super(character, "Streetwise", Attribute.Intelligence, Difficulty.Average);
    }
}
