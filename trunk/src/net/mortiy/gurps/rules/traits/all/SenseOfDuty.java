package net.mortiy.gurps.rules.traits.all;

import net.mortiy.gurps.rules.Individual;
import net.mortiy.gurps.rules.traits.Disadvantage;
import net.mortiy.gurps.rules.traits.FixedTrait;

/**
 * Created with IntelliJ IDEA.
 * User: oleksandr.sidko
 * Date: 12.12.12
 * Time: 23:20
 * To change this template use File | Settings | File Templates.
 */
public class SenseOfDuty extends FixedTrait implements Disadvantage {
    public SenseOfDuty(Individual individual) {
        super(individual, "Sense of Duty ", -5);
    }
}
