package net.mortiy.gurps.rules.traits.all;

import net.mortiy.gurps.rules.*;
import net.mortiy.gurps.rules.Character;
import net.mortiy.gurps.rules.traits.Disadvantage;
import net.mortiy.gurps.rules.traits.FixedTrait;

/**
 * Created with IntelliJ IDEA.
 * User: oleksandr.sidko
 * Date: 12.12.12
 * Time: 23:19
 * To change this template use File | Settings | File Templates.
 */
public class LightSleeper extends FixedTrait implements Disadvantage {

    public LightSleeper(Character character) {
        super(character, "Light Sleeper", -5);
    }
}
