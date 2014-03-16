package net.mortiy.gurps.rules.hazards;

import net.mortiy.gurps.rules.Individual;
import net.mortiy.gurps.rules.modifiers.Modifier;

import java.util.ArrayList;
import java.util.List;

public abstract class Affliction extends Hazard {

    protected Affliction(Individual character) {
        super(character);
    }

    public void detach() {
        for (Modifier m : modifiers) {
            m.detach();
        }
    }

}
