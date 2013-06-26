package net.mortiy.gurps.rules.equipment.armor;

import net.mortiy.gurps.rules.TechLevel;
import net.mortiy.gurps.rules.character.Body;
import net.mortiy.gurps.rules.equipment.ArmorItem;
import net.mortiy.gurps.rules.equipment.DamageResistance;

public class ClothArmor extends ArmorItem {

    // TODO: This armor is flexible ("*", p. 282)
    public ClothArmor() {
        super(
                "Cloth Armor",
                TechLevel.TL0,
                new Body.Part[]{ Body.Part.Torso, Body.Part.Groin },
                new DamageResistance(1),  // DR
                30, // Cost
                6   // Weight
        );
    }
}
