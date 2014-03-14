package net.mortiy.gurps.rules.traits;

import net.mortiy.gurps.rules.Individual;

/**
 * Created with IntelliJ IDEA.
 * User: oleksandr.sidko
 * Date: 06.12.12
 * Time: 1:52
 * To change this template use File | Settings | File Templates.
 */
public class FixedTrait extends Trait {
    public FixedTrait(Individual individual, String name, Integer cost) {
        super(individual, name, CostType.Fixed, cost);
    }
}
