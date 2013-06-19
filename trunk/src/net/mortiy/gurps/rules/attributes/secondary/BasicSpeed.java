package net.mortiy.gurps.rules.attributes.secondary;

import net.mortiy.gurps.rules.attributes.Attribute;
import net.mortiy.gurps.rules.attributes.SecondaryCharacteristic;

/**
 * Your Basic Speed is a measure of your reflexes and general physical
 * quickness. It helps determine your running speed (see  Basic Move,
 * below), your chance of dodging an attack, and the order in which you act
 * in combat (a high Basic Speed will let you "out-react" your foes).
 */
public class BasicSpeed extends SecondaryCharacteristic {
    public BasicSpeed(net.mortiy.gurps.rules.Character character) {
        super(character);
        attribute = Attribute.BasicSpeed;
        levelCost = 5;
        levelIncrement = 0.25f;
    }

    @Override
    public float getValue() {
        int healthLevel = character.getBasicAttribute(Attribute.Health).getLevel();
        int dexterityLevel = character.getBasicAttribute(Attribute.Dexterity).getLevel();

        return level + (healthLevel + dexterityLevel) / 4f;
    }

}
