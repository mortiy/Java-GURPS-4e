package net.mortiy.gurps.rules.skills.all;

import net.mortiy.gurps.rules.*;
import net.mortiy.gurps.rules.Character;
import net.mortiy.gurps.rules.attributes.Attribute;
import net.mortiy.gurps.rules.skills.Skill;

/**
 * Lockpicking
 * ===========================
 */
public class Lockpicking extends Skill {
    public Lockpicking(Character character) {
        super(character, "Lockpicking", Attribute.Intelligence, Difficulty.Average);
    }
}
