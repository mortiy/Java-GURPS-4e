package net.mortiy.gurps.rules.skills.all;

import net.mortiy.gurps.rules.attributes.Attribute;
import net.mortiy.gurps.rules.skills.Skill;
import net.mortiy.gurps.rules.Character;
import net.mortiy.gurps.rules.skills.modifiers.TaskDifficulty;

/**
 * Body Sense
 * ========================
 * Description:
 */
public class BodySense extends Skill {
    public BodySense(Character character) {
        super(character, "Body Sense", Attribute.Dexterity, Difficulty.Hard);
    }
}
