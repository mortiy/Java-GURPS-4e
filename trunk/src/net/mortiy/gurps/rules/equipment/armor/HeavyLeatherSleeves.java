package net.mortiy.gurps.rules.equipment.armor;

import net.mortiy.gurps.rules.TechLevel;
import net.mortiy.gurps.rules.equipment.DamageResistance;

public class HeavyLeatherSleeves extends Sleeves {
    public HeavyLeatherSleeves() {
        super(
                "Heavy Leather Sleeves",
                TechLevel.TL1,
                new DamageResistance(2),  // DR
                50,
                2
        );
    }
}
