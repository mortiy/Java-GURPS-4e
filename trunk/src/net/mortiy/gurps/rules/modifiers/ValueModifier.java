package net.mortiy.gurps.rules.modifiers;

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
