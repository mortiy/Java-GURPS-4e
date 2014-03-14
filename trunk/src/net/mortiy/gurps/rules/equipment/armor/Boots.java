package net.mortiy.gurps.rules.equipment.armor;

import net.mortiy.gurps.rules.TechLevel;
import net.mortiy.gurps.rules.equipment.ArmorItem;
import net.mortiy.gurps.rules.equipment.DamageResistance;
import net.mortiy.gurps.rules.individual.Body;

public class Boots extends ArmorItem implements Footwear {
    public Boots() {
        super(
                "Boots",
                TechLevel.TL2,
                new Body.Part[]{Body.Part.Feet},
                new DamageResistance(2),
                80,
                3
        );
    }
}
