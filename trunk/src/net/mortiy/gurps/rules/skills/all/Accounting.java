package net.mortiy.gurps.rules.skills.all;

import net.mortiy.gurps.rules.Individual;
import net.mortiy.gurps.rules.attributes.Attribute;
import net.mortiy.gurps.rules.skills.Skill;
import net.mortiy.gurps.rules.skills.SkillDefault;

/**
 * This is the ability to keep books of account, to examine the condition of a
 business, etc. A successful Accounting roll (requires at least two hours of
 study, and possibly months to audit a large corporation) can tell you whether
 financial records are correct, and possibly reveal evidence of forgery, tampering,
 and similar criminal activity.
 */
public class Accounting extends Skill {
    public Accounting(Individual individual) {
        super(individual, "Accounting", Attribute.Intelligence, Difficulty.Hard);

        // region "Skill Defaults"
        skillDefaults.add(
                new SkillDefault(Attribute.Intelligence, -6)
        );

        try {
            setDefault("Finance", -4);
            setDefault("Mathematics (Statistics)", -5);
            setDefault("Merchant", -5);
        } catch (Skill.UnknownSkillDefaultSkillException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        // endregion

    }
}
