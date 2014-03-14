package net.mortiy.gurps.rules.skills.all;

import net.mortiy.gurps.rules.Individual;
import net.mortiy.gurps.rules.attributes.Attribute;
import net.mortiy.gurps.rules.skills.Skill;

/**
 * Shield
 * ===============================
 * This is the ability to use a shield,
 * both to block and to attack. Your
 * active defense with any kind of shield
 * – your Block score – is (skill/2) + 3,
 * rounded down.
 */
public class Shield extends Skill {
    public enum Speciality {
        Shield,
        Buckler,
        Force
    }

    public Shield(Individual individual, Speciality speciality) {
        super(individual, "Shield", speciality.toString(), Attribute.Dexterity, Difficulty.Easy);
    }
}
