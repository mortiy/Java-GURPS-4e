package net.mortiy.gurps.rules.skills.all;

import net.mortiy.gurps.rules.attributes.Attribute;
import net.mortiy.gurps.rules.skills.Skill;
import net.mortiy.gurps.rules.skills.SkillDefault;

/**
 * Melee Weapon
 * ==============================
 * This is not one skill, but an entire
 collection of skills – one per class of
 closely related melee weapons. Melee
 Weapon skills are based on DX, and
 default to DX-4 if Easy, DX-5 if
 Average, or DX-6 if Hard. See specific
 skill descriptions for other defaults.
 */
public class MeleeWeapon extends Skill {

    public MeleeWeapon(net.mortiy.gurps.rules.Character character, String name, Difficulty difficulty) {
        this(character, name, "", difficulty);
    }

    public MeleeWeapon(net.mortiy.gurps.rules.Character character, String name, String speciality, Difficulty difficulty) {
        super(character, name, speciality, Attribute.Dexterity, difficulty);

        skillDefaults.add(
                new SkillDefault(Attribute.Dexterity, -(4 + difficulty.ordinal()))
        );
    }

    public int getParry(){
        // Your Parry defense is (skill/2) + 3, rounded down.
        return (int) Math.floor(getSkillLevel() / 2 + 3);
    };
}
