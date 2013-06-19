package net.mortiy.gurps.rules.skills.all;

import net.mortiy.gurps.rules.attributes.Attribute;
import net.mortiy.gurps.rules.skills.Skill;
import net.mortiy.gurps.rules.Character;

/**
 * Urban Survival
 * ========================
 * Description:
 */
public class UrbanSurvival extends Skill {
    public UrbanSurvival(Character character) {
        super(character, "Urban Survival", Attribute.Perception, Difficulty.Average);
    }
}
