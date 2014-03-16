package net.mortiy.gurps.rules.hazards.afflictions;

import net.mortiy.gurps.rules.Individual;
import net.mortiy.gurps.rules.attributes.Attribute;
import net.mortiy.gurps.rules.hazards.Affliction;
import net.mortiy.gurps.rules.modifiers.BanModifier;
import net.mortiy.gurps.rules.modifiers.SummandModifier;

public class Sneezing extends Affliction {

    protected Sneezing(Individual character) {
        super(character);
    }

    @Override
    public int affect() {
        addModifier(c.getBasicAttribute(Attribute.Dexterity), new SummandModifier(-3f));
        addModifier(c.getBasicAttribute(Attribute.Intelligence), new SummandModifier(-1f));
        addModifier(c.getSkill("Stealth"), new BanModifier());

        return 0;
    }

}
