package net.mortiy.gurps.rules.equipment.armor;

import net.mortiy.gurps.rules.TechLevel;
import net.mortiy.gurps.rules.equipment.ArmorItem;
import net.mortiy.gurps.rules.equipment.DamageResistance;
import net.mortiy.gurps.rules.individual.Body;

public class LeatherArmor extends ArmorItem {

    // TODO: This armor is flexible ("*", p. 282)
    public LeatherArmor() {
        super(
                "Leather Armor",
                TechLevel.TL1,
                new Body.Part[]{ Body.Part.Torso, Body.Part.Groin },
                new DamageResistance(2),  // DR
                100, // Cost
                10   // Weight
        );
    }
}
