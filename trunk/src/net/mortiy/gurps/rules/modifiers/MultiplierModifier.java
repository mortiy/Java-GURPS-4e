package net.mortiy.gurps.rules.modifiers;


public class MultiplierModifier extends ValueModifier {
    public enum  Mode {
        RoundUp,
        RoundDown
    }
    Mode mode;
    public MultiplierModifier(float amount, Mode mode) {
        super(amount);
        this.mode = mode;
    }

    @Override
    public float getValue(float modifiable) {
        float value = modifiable * amount;
        switch (mode){
            case RoundUp:
                return (int) Math.ceil(value);
            case RoundDown:
                return (int) Math.floor(value);
            default:
                return value;
        }

    }
}
