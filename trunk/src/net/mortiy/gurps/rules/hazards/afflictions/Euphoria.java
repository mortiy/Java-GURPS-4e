package net.mortiy.gurps.rules.hazards.afflictions;

import net.mortiy.gurps.rules.Individual;
import net.mortiy.gurps.rules.attributes.Attribute;
import net.mortiy.gurps.rules.hazards.Affliction;
import net.mortiy.gurps.rules.modifiers.SummandModifier;
import net.mortiy.gurps.rules.table.Rolls;

public class Euphoria extends Affliction {
    public Euphoria(Individual character) {
        super(character);
    }

    @Override
    public int affect() {

        SummandModifier summandModifier = new SummandModifier(-3);

        addModifier(Attribute.Dexterity, summandModifier);
        addModifier(Attribute.Intelligence, summandModifier);
        addModifier(Rolls.SelfControlRoll, summandModifier);
        addModifier(Rolls.SkillRoll, summandModifier);
        return 0;
    }
}
