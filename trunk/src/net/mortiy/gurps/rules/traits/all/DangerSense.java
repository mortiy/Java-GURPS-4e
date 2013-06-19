package net.mortiy.gurps.rules.traits.all;

import net.mortiy.gurps.rules.Character;
import net.mortiy.gurps.rules.traits.Advantage;
import net.mortiy.gurps.rules.traits.FixedTrait;
import net.mortiy.gurps.rules.traits.categories.Mental;

/**
 * Created with IntelliJ IDEA.
 * User: oleksandr.sidko
 * Date: 11.12.12
 * Time: 21:51
 * To change this template use File | Settings | File Templates.
 */
public class DangerSense extends FixedTrait implements Advantage, Mental {
    public DangerSense(Character character) {
        super(character, "Danger Sense", 15);
    }
}
