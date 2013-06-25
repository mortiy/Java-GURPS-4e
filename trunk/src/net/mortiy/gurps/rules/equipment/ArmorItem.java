package net.mortiy.gurps.rules.equipment;

import net.mortiy.gurps.rules.TechLevel;
import net.mortiy.gurps.rules.character.Body;
import net.mortiy.gurps.rules.character.BodyLocation;

import java.util.Arrays;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: oleksandr.sidko
 * Date: 25.06.13
 * Time: 23:10
 * To change this template use File | Settings | File Templates.
 */
public class ArmorItem extends Item {

    protected DamageResistance damageResistance;
    protected List<Body.Part> bodyLocations;

    public ArmorItem(String name, TechLevel.Level techLevel, Body.Part bodyLocations[], DamageResistance damageResistance, float cost, float weight) {
        super(name, techLevel, cost, weight);
        this.bodyLocations = Arrays.asList(bodyLocations);
        this.damageResistance = damageResistance;
    }

    public DamageResistance getDamageResistance(){
        return damageResistance;
    }
    public boolean isSuitableFor(Body.Part bodyPart){
        return bodyLocations.contains(bodyPart);
    }
}
