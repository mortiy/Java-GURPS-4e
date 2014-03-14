package net.mortiy.gurps.rules.traits;

import net.mortiy.gurps.rules.Individual;

/**
 * Created with IntelliJ IDEA.
 * User: oleksandr.sidko
 * Date: 12.12.12
 * Time: 23:38
 * To change this template use File | Settings | File Templates.
 */
public class Quirk extends FixedTrait implements Disadvantage {
    public Quirk(Individual individual, String name) {
        super(individual, name, -1);
    }
}
