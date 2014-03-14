package net.mortiy.gurps.rules.skills.all;

import net.mortiy.gurps.rules.Individual;
import net.mortiy.gurps.rules.attributes.Attribute;
import net.mortiy.gurps.rules.skills.Skill;
import net.mortiy.gurps.rules.skills.SkillDefault;

/**
 * Swimming
 * ================================
 * This is the skill of swimming (whether on purpose or to keep afloat
 * in emergencies) and lifesaving. Roll against the higher of Swimming or HT
 * to avoid fatigue while swimming or injury due to aquatic misfortunes
 * When racing someone of equal water Move, roll a Quick Contest of
 * Swimming to determine the winner. See Swimming (p. 354).
 * Note that Swimming does not cover high diving – that’s Sports(Diving).
 */
public class Swimming extends Skill {
    public Swimming(Individual individual) {
        super(individual, "Swimming", Attribute.Health, Difficulty.Easy);
        skillDefaults.add(
                new SkillDefault(Attribute.Health, -4)
        );
    }
}
