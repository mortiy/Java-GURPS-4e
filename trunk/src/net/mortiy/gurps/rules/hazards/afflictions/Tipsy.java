package net.mortiy.gurps.rules.hazards.afflictions;

import net.mortiy.gurps.rules.Individual;
import net.mortiy.gurps.rules.attributes.Attribute;
import net.mortiy.gurps.rules.hazards.Affliction;
import net.mortiy.gurps.rules.modifiers.SummandModifier;
import net.mortiy.gurps.rules.table.Rolls;

public class Tipsy extends Affliction {
    public Tipsy(Individual character) {
        super(character);
    }

    @Override
    public int affect() {

        SummandModifier attributeModifier = new SummandModifier(-1);
        SummandModifier rollModifier = new SummandModifier(-2);

        addModifier(Attribute.Dexterity, attributeModifier);
        addModifier(Attribute.Intelligence, attributeModifier);
        addModifier(Rolls.SelfControlRoll, rollModifier);

        //FIXME: If Shyness added after Tipsy effect started, it's levels isn't modified
        if (c.hasTrait("Shyness")) {
            addModifier(c.getTrait("Shyness"), new SummandModifier(-1));
        }

        return 0;
    }
}
