package net.mortiy.gurps.rules.hazards;

import net.mortiy.gurps.rules.Individual;
import net.mortiy.gurps.rules.modifiers.Modifier;
import net.mortiy.gurps.rules.modifiers.ValueModifier;

import java.util.ArrayList;
import java.util.List;

abstract public class Hazard {
    protected Individual c;
    protected List<Modifier> modifiers = new ArrayList<>();

    protected Hazard(Individual character) {
        this.c = character;
    }

    protected void addModifier(Modifier.IModifiable modifiedEntity, ValueModifier valueModifier) {
        modifiers.add(c.addModifier(modifiedEntity, valueModifier));
    }

    public abstract int affect();

    public void remove() {
        for (Modifier m : modifiers) {
            c.removeModifier(m);
        }
    }
}
