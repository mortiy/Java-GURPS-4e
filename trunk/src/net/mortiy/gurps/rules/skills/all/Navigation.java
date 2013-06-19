package net.mortiy.gurps.rules.skills.all;

import net.mortiy.gurps.rules.attributes.Attribute;
import net.mortiy.gurps.rules.skills.Skill;
import net.mortiy.gurps.rules.Character;

/**
 * Navigation
 * ========================
 * Description:
 */
public class Navigation extends Skill {
    public Navigation(Character character) {
        super(character, "Navigation", Attribute.Intelligence, Difficulty.Average);
    }
}
