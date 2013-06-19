package net.mortiy.gurps.rules.skills.all;

import net.mortiy.gurps.rules.*;
import net.mortiy.gurps.rules.Character;
import net.mortiy.gurps.rules.attributes.Attribute;
import net.mortiy.gurps.rules.skills.Skill;

/**
 * Created with IntelliJ IDEA.
 * User: oleksandr.sidko
 * Date: 12.12.12
 * Time: 23:45
 * To change this template use File | Settings | File Templates.
 */
public class Stealth extends Skill {
    public Stealth(Character character) {
        super(character, "Stealth", Attribute.Dexterity, Difficulty.Average);
    }
}
