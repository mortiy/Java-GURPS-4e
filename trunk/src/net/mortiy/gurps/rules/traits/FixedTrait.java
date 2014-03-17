package net.mortiy.gurps.rules.traits;

import net.mortiy.gurps.rules.Individual;

public class FixedTrait extends Trait {
    public FixedTrait(Individual individual, String name, Integer cost) {
        super(individual, name, CostType.Fixed, cost);
    }
}
