package net.mortiy.gurps.rules.equipment.armor;

import net.mortiy.gurps.rules.TechLevel;
import net.mortiy.gurps.rules.equipment.ArmorItem;
import net.mortiy.gurps.rules.equipment.DamageResistance;
import net.mortiy.gurps.rules.individual.Body;

public class Leggings extends ArmorItem implements LimbArmor {
    public Leggings(String name, TechLevel techLevel, DamageResistance damageResistance, float cost, float weight) {
        super(name, techLevel, new Body.Part[]{Body.Part.Legs}, damageResistance, cost, weight);
    }
}
