package net.mortiy.gurps.rules.skills.all;

import net.mortiy.gurps.rules.attributes.Attribute;
import net.mortiy.gurps.rules.skills.Skill;
import net.mortiy.gurps.rules.Character;

/**
 * Fast-Talk
 * ========================
 * Description:
 */
public class FastTalk extends Skill {
    public FastTalk(Character character) {
        super(character, "Fast-Talk", Attribute.Intelligence, Difficulty.Average);
    }
}
