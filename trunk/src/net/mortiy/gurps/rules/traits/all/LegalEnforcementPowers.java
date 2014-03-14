package net.mortiy.gurps.rules.traits.all;

import net.mortiy.gurps.rules.Individual;
import net.mortiy.gurps.rules.traits.Advantage;
import net.mortiy.gurps.rules.traits.VariableTrait;
import net.mortiy.gurps.rules.traits.categories.Social;

/**
 * Legal Enforcement Powers
 * ================================================
 * You are a law enforcer, with the accompanying powers and
 * restrictions. In some times and places, this amounts to a
 * license to kill. In others, it’s little more than the right to
 * carry a badge and write parking tickets.
 * The point cost depends on the kinds of laws you enforce, the size of
 * your jurisdiction, how answerable you are for your actions, and the degree of
 * respect you must show for the civil rights of others.
 * TODO: Legal Enforcement Powers almost always require an appropriate Duty
 */
public class LegalEnforcementPowers extends VariableTrait implements Advantage, Social {

    public static enum Level implements ITraitLevel {
        /**
         * You have local jurisdiction, the ability to arrest suspected criminals,
         * the power to perform searches with an appropriate warrant, and possibly the
         * right to carry a concealed weapon.
         * Examples: a Victorian bobby or a modern policeman.
         */
        Local,
        /**
         * <div>As Local, but you also have national or international jurisdiction,
         * or are not obligated to respect the civil rights of others, or are free to engage
         * in covert investigations,  or may kill with relative impunity<br/>
         * <b>Examples:</b> an FBI agent or a medieval Royal Guardsman
         * </div>
         */
        National,
        /**
         * You have three or more of <b>Local</b> or <b>National</b> abilities.
         */
        Special,
    }

    public LegalEnforcementPowers(Individual individual) {
        super(individual, "Legal Enforcement Powers");
        levelsCost = new int[]{5, 10, 15};
    }
}
