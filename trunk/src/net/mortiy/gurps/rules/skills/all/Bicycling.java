package net.mortiy.gurps.rules.skills.all;

import net.mortiy.gurps.rules.attributes.Attribute;
import net.mortiy.gurps.rules.skills.Skill;
import net.mortiy.gurps.rules.skills.SkillDefault;
import net.mortiy.gurps.rules.Character;
/**
 * Created with IntelliJ IDEA.
 * User: oleksandr.sidko
 * Date: 08.12.12
 * Time: 4:11
 * To change this template use File | Settings | File Templates.
 */
public class Bicycling extends Skill {
    public Bicycling(Character character) {
        super(character, "Bicycling", Attribute.Dexterity, Difficulty.Easy);
        attribute = Attribute.Dexterity;
        difficulty = Difficulty.Easy;

        // region "Skill Defaults"
        skillDefaults.add(
                new SkillDefault(Attribute.Dexterity, -4)
        );

        skillDefaults.add(
                new SkillDefault(
                        new Skill(character, "Driving", "Motorcycle", Attribute.Dexterity, Difficulty.Average), -4)
        );

        // endregion

    }
}
