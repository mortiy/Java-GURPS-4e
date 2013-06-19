package net.mortiy.gurps.rules.attributes;
import net.mortiy.gurps.rules.Character;
import net.mortiy.gurps.rules.skills.interfaces.ISkillDefault;

/**
 * "Secondary characteristics" are quantities that depend directly on your attributes.
 * You can raise or lower these scores by adjusting your attributes.
 * You can modify some of them directly: start with the value calculated from your
 * attributes and spend the required points to adjust it away from that base level.
 * This does not affect the related attribute scores.
 */
public class SecondaryCharacteristic extends CharacterAttribute implements ISkillDefault {
    protected Character character;
    protected float currentValue;
    public SecondaryCharacteristic(Character character) {
        super(Attribute.Unknown);
        this.character = character;
        this.currentValue = getValue();
    }

    public void modifyValue(int modifier){
        currentValue += modifier;

        // Current value can't be higher than maximum one:
        if(currentValue > getValue()){
            currentValue = getValue();
        }
    }

    public float getCurrentValue() {
        return currentValue;
    }
}
