package net.mortiy.gurps.rules.skills.all;

import net.mortiy.gurps.rules.*;
import net.mortiy.gurps.rules.Character;
import net.mortiy.gurps.rules.attributes.Attribute;
import net.mortiy.gurps.rules.skills.Skill;
import net.mortiy.gurps.rules.skills.SkillDefault;

/**
 * Created with IntelliJ IDEA.
 * User: oleksandr.sidko
 * Date: 10.12.12
 * Time: 2:33
 * To change this template use File | Settings | File Templates.
 */
public class Merchant extends Skill {
    public Merchant(Character character) {
        super(character, "Merchant", Attribute.Intelligence, Difficulty.Average);

    }
}
