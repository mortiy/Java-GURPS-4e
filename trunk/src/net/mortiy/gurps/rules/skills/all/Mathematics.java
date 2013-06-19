package net.mortiy.gurps.rules.skills.all;

import net.mortiy.gurps.rules.Character;
import net.mortiy.gurps.rules.attributes.Attribute;
import net.mortiy.gurps.rules.skills.Skill;

public class Mathematics extends Skill {

    public Mathematics(Character character) {
        this(character, "");
    }

    public Mathematics(Character character, String speciality) {
        super(character, "Mathematics", speciality, Attribute.Intelligence, Difficulty.Hard);
    }
}
