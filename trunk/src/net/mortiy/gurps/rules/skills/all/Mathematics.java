package net.mortiy.gurps.rules.skills.all;

import net.mortiy.gurps.rules.Individual;
import net.mortiy.gurps.rules.attributes.Attribute;
import net.mortiy.gurps.rules.skills.Skill;

public class Mathematics extends Skill {

    public Mathematics(Individual individual) {
        this(individual, "");
    }

    public Mathematics(Individual individual, String speciality) {
        super(individual, "Mathematics", speciality, Attribute.Intelligence, Difficulty.Hard);
    }
}
