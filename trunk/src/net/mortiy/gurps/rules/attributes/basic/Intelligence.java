package net.mortiy.gurps.rules.attributes.basic;

import net.mortiy.gurps.rules.attributes.Attribute;
import net.mortiy.gurps.rules.attributes.BasicAttribute;

/**
 * Intelligence broadly measures
 brainpower, including creativity, intuition,
 memory, perception, reason,
 sanity, and willpower. It rules your
 basic ability with all “mental” skills –
 sciences, social interaction, magic,
 etc
 */
public class Intelligence extends BasicAttribute {
    public Intelligence() {
        super();
        attribute = Attribute.Intelligence;
        levelCost = 20;
    }
}
