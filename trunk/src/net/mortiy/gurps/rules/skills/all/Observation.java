package net.mortiy.gurps.rules.skills.all;
import net.mortiy.gurps.rules.attributes.Attribute;
import net.mortiy.gurps.rules.skills.Skill;
import net.mortiy.gurps.rules.Character;

/**
 * Observation
 * ========================
 * Description:
 */
public class Observation extends Skill {
    public Observation(Character character) {
        super(character, "Observation", Attribute.Perception, Difficulty.Average);
    }
}
