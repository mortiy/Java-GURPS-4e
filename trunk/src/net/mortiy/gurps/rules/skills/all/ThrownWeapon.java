package net.mortiy.gurps.rules.skills.all;

import net.mortiy.gurps.rules.attributes.Attribute;
import net.mortiy.gurps.rules.skills.Skill;
import net.mortiy.gurps.rules.Character;

/**
 * Thrown Weapon
 * ========================
 * Description:
 */
public class ThrownWeapon extends Skill {
    public ThrownWeapon(Character character, String speciality) {
        super(character, "Thrown Weapon", speciality, Attribute.Dexterity, Difficulty.Easy);
    }
}
