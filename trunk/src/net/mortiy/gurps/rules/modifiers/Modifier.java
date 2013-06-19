package net.mortiy.gurps.rules.modifiers;

import net.mortiy.gurps.rules.Character;

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
    private net.mortiy.gurps.rules.Character character;
    private ValueModifier modifierValue;

    public Modifier(Character character, IModifiable modifiedEntity, ValueModifier modifierValue) {
        this.character = character;
        this.modifiedEntity = modifiedEntity;
        this.modifierValue = modifierValue;
        this.character.registerModifier(this);
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
        if(character != null){
            character.removeModifier(this);
            character = null;
        }
    }



}
