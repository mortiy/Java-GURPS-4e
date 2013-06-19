package net.mortiy.gurps.rules.skills.all;

import net.mortiy.gurps.rules.attributes.Attribute;
import net.mortiy.gurps.rules.skills.Skill;
import net.mortiy.gurps.rules.Character;

/**
 * Fast-Draw
 * ========================
 * Description:
 */
public class FastDraw extends Skill {
    public FastDraw(Character character, String speciality) {
        super(character, "Fast-Draw", speciality, Attribute.Dexterity, Difficulty.Easy);
    }
}
