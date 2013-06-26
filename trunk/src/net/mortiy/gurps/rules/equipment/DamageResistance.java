package net.mortiy.gurps.rules.equipment;

import net.mortiy.gurps.rules.combat.Damage;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents armor's damage resistance (p. 282)
 * TODO: Improve resistance handling.
 */
public class DamageResistance {
    protected Map<Damage.Type, Integer> damageResistances = new HashMap<>();
    protected int defaultResistance;

    public DamageResistance(int defaultResistance) {
        this.defaultResistance = defaultResistance;
    }

    public int getResistance(Damage.Type damageType){
        if(damageResistances.containsKey(damageType)){
            return damageResistances.get(damageType);
        }
        return defaultResistance;
    }

    public void addResistance(Damage.Type damageType, int value){
        if(damageResistances.containsKey(damageType)){
            value += damageResistances.get(damageType);
        }
        damageResistances.put(damageType, value);
    }
}
