package net.mortiy.gurps.rules.skills.all;

import net.mortiy.gurps.rules.attributes.Attribute;
import net.mortiy.gurps.rules.skills.Skill;
import net.mortiy.gurps.rules.Character;

/**
 * Holdout
 * ========================
 * Description:
 */
public class Holdout extends Skill {
    public Holdout(Character character) {
        super(character, "Holdout", Attribute.Intelligence, Difficulty.Average);
    }
}
