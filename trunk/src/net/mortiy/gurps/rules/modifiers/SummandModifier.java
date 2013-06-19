package net.mortiy.gurps.rules.modifiers;

/**
 * Created with IntelliJ IDEA.
 * User: oleksandr.sidko
 * Date: 10.02.13
 * Time: 21:17
 * To change this template use File | Settings | File Templates.
 */
public class SummandModifier extends ValueModifier {
    public SummandModifier(float amount) {
        super(amount);
    }

    @Override
    public float getValue(float modifiable) {
        return modifiable + amount;
    }
}
