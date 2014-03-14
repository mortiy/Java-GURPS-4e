package net.mortiy.gurps.rules.equipment;

import net.mortiy.gurps.rules.TechLevel;
import net.mortiy.gurps.rules.individual.Body;
import net.mortiy.gurps.rules.combat.Damage;

import java.util.Arrays;
import java.util.List;

public class ArmorItem extends Item {

    protected DamageResistance damageResistance;
    protected List<Body.Part> bodyLocations;

    public ArmorItem(String name, TechLevel techLevel, Body.Part bodyLocations[], DamageResistance damageResistance, float cost, float weight) {
        super(name, techLevel, cost, weight);
        this.bodyLocations = Arrays.asList(bodyLocations);
        this.damageResistance = damageResistance;
    }

    public int getDamageResistance(Damage.Type damageType){
        return damageResistance.getResistance(damageType);
    }
    public boolean isSuitableFor(Body.Part bodyPart){
        return bodyLocations.contains(bodyPart);
    }
}
