package net.mortiy.gurps.rules.hazards.afflictions;

import net.mortiy.gurps.rules.Individual;
import net.mortiy.gurps.rules.attributes.Attribute;
import net.mortiy.gurps.rules.hazards.Affliction;
import net.mortiy.gurps.rules.modifiers.SummandModifier;
import net.mortiy.gurps.rules.table.Rolls;

public class Drunk extends Affliction {
    protected Drunk(Individual character) {
        super(character);
    }

    @Override
    public int affect() {

        addModifier(c.getBasicAttribute(Attribute.Dexterity), new SummandModifier(-2f));
        addModifier(c.getBasicAttribute(Attribute.Intelligence), new SummandModifier(-2f));
        addModifier(Rolls.SelfControlRoll, new SummandModifier(-4));

        return 0;
    }
}
