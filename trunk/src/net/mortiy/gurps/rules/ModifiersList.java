package net.mortiy.gurps.rules;

import net.mortiy.gurps.rules.modifiers.Modifier;
import net.mortiy.gurps.rules.modifiers.MultiplierModifier;
import net.mortiy.gurps.rules.modifiers.SummandModifier;

import java.util.LinkedList;

public class ModifiersList extends LinkedList<Modifier> {
    public float getTotal(){
        float total = 0.0f;
        for(Modifier m : this){
            total = m.getValueModifier().getValue(total);
        }
        return total;
    }

    public float getTotalSummand(){
        float totalSummand = 0.0f;
        for(Modifier m : this){
            if(m.getValueModifier() instanceof SummandModifier){
                totalSummand += m.getValueModifier().getAmount();
            }
        }
        return totalSummand;
    }

    public float getTotalMultiplier(){
        float totalMultiplier = 0.0f;
        for(Modifier m : this){
            if(m.getValueModifier() instanceof MultiplierModifier){
                totalMultiplier *= m.getValueModifier().getAmount();
            }
        }
        return totalMultiplier;
    }

    @Override
    public boolean add(Modifier object) {
        return super.add(object);    //To change body of overridden methods use File | Settings | File Templates.
    }
}
