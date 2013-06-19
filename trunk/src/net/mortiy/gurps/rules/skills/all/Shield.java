package net.mortiy.gurps.rules.skills.all;

import net.mortiy.gurps.rules.*;
import net.mortiy.gurps.rules.Character;
import net.mortiy.gurps.rules.attributes.Attribute;
import net.mortiy.gurps.rules.skills.Skill;

/**
 * Shield
 * ===============================
 * This is the ability to use a shield,
 * both to block and to attack. Your
 * active defense with any kind of shield
 * – your Block score – is (skill/2) + 3,
 * rounded down.
 */
public class Shield extends Skill {
    public enum Speciality {
        Shield,
        Buckler,
        Force
    }

    public Shield(Character character, Speciality speciality) {
        super(character, "Shield", speciality.toString(), Attribute.Dexterity, Difficulty.Easy);
    }
}
