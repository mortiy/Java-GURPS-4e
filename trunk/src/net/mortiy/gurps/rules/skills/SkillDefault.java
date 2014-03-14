package net.mortiy.gurps.rules.skills;

import net.mortiy.gurps.rules.Individual;
import net.mortiy.gurps.rules.attributes.Attribute;
import net.mortiy.gurps.rules.skills.interfaces.ISkillDefault;

public class SkillDefault {
    public class DefaultSkillNotFoundException extends Throwable {
    }

    public ISkillDefault skillDefault;
    int modifier;

    public SkillDefault(ISkillDefault skillDefault, int modifier) {
        this.skillDefault = skillDefault;
        this.modifier = modifier;
    }

    public int getLevel(Individual individual) {
        int level = 0;
        // Default by Basic Attribute:
        if (skillDefault instanceof Attribute) {
            level = individual.getBasicAttribute((Attribute) skillDefault).getLevel();

            // "The Rule of 20" for Basic Attribute greater than 20 (see p.344)
            if (level > 20) {
                level = 20;
            }
        }
        // Default by Skill:
        else if (skillDefault instanceof Skill) {
            String skillKey = ((Skill) skillDefault).getKey();

            if (individual.hasLearntSkill(skillKey)) {
                Skill skill = individual.getSkill(skillKey);
                level = skill.getLevel();
            }

        }
        return level;
    }

}
