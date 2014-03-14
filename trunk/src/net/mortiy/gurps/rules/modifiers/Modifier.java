package net.mortiy.gurps.rules.modifiers;

import net.mortiy.gurps.rules.Individual;

public class Modifier {

    /**
     * Represents Attribute/Skill/Trait/Roll
     * which can be influenced by different
     * kind of modifiers.
     */
    public static interface IModifiable {}

    /**
     * Something which can influence the modifier's value.
     */
    public interface IInfluential {}

    public IModifiable modifiedEntity;
    private Individual individual;
    private ValueModifier modifierValue;

    public Modifier(Individual individual, IModifiable modifiedEntity, ValueModifier modifierValue) {
        this.individual = individual;
        this.modifiedEntity = modifiedEntity;
        this.modifierValue = modifierValue;
        this.individual.registerModifier(this);
    }

    public IModifiable getModifiedEntity() {
        return modifiedEntity;
    }


    public ValueModifier getValueModifier() {
        return modifierValue;
    }

    public void setModifierValue(ValueModifier valueModifier) {
        this.modifierValue = valueModifier;
    }


    /**
     * Removed
     */
    public void detach(){
        if(individual != null){
            individual.removeModifier(this);
            individual = null;
        }
    }



}
