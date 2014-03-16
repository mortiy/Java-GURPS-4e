package net.mortiy.gurps.rules.modifiers;

/**
 * Used to disable some skills
 */
public class BanModifier extends MultiplierModifier {

    public BanModifier() {
        super(0, Mode.RoundDown);
    }

}
