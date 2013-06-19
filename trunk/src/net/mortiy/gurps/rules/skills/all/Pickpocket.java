package net.mortiy.gurps.rules.skills.all;

import net.mortiy.gurps.rules.Character;
import net.mortiy.gurps.rules.attributes.Attribute;
import net.mortiy.gurps.rules.skills.Skill;

/**
 * Pickpocket
 * =============================
 *
 */
public class Pickpocket extends Skill {

    public Pickpocket(Character character) {
        super(character, "Pickpocket", Attribute.Dexterity, Difficulty.Hard);
    }
}
