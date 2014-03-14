package net.mortiy.gurps.rules.equipment.armor;

import net.mortiy.gurps.rules.TechLevel;
import net.mortiy.gurps.rules.equipment.ArmorItem;
import net.mortiy.gurps.rules.equipment.DamageResistance;
import net.mortiy.gurps.rules.individual.Body;

public class Sleeves extends ArmorItem implements LimbArmor {
    public Sleeves(String name, TechLevel techLevel, DamageResistance damageResistance, float cost, float weight) {
        super(name, techLevel, new Body.Part[]{Body.Part.Arms}, damageResistance, cost, weight);
    }
}
