package net.mortiy.gurps.rules.modifiers;

/**
 * Created with IntelliJ IDEA.
 * User: oleksandr.sidko
 * Date: 10.02.13
 * Time: 21:16
 * To change this template use File | Settings | File Templates.
 */
public abstract class ValueModifier {
    float amount;

    public ValueModifier(float amount) {
        this.amount = amount;
    }

    public abstract float getValue(float modifiable);

    public float getAmount(){
        return amount;
    }
}
