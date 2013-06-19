package net.mortiy.gurps.rules.attributes.secondary;

import net.mortiy.gurps.rules.attributes.SecondaryCharacteristic;

/**
 * Created with IntelliJ IDEA.
 * User: oleksandr.sidko
 * Date: 10.02.13
 * Time: 21:39
 * To change this template use File | Settings | File Templates.
 */
public class Dodge extends SecondaryCharacteristic {
    public Dodge(net.mortiy.gurps.rules.Character character) {
        super(character);
    }

    @Override
    public float getValue() {
        return (int) Math.floor(character.getBasicSpeed().getValue()) + 3 - character.getEncumbrance().ordinal();
    }

}
