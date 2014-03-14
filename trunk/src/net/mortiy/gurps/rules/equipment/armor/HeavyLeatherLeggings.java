package net.mortiy.gurps.rules.equipment.armor;

import net.mortiy.gurps.rules.TechLevel;
import net.mortiy.gurps.rules.equipment.DamageResistance;

public class HeavyLeatherLeggings extends Leggings {
    public HeavyLeatherLeggings() {
        super(
                "Heavy Leather Leggings",
                TechLevel.TL1,
                new DamageResistance(2),  // DR
                60,
                4
        );
    }
}
