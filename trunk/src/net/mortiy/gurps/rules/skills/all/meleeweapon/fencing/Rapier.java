package net.mortiy.gurps.rules.skills.all.meleeweapon.fencing;

import net.mortiy.gurps.rules.attributes.Attribute;
import net.mortiy.gurps.rules.skills.Skill;
import net.mortiy.gurps.rules.Character;
import net.mortiy.gurps.rules.skills.all.meleeweapon.FencingMeleeWeapon;

/**
 * Rapier
 * ========================
 * Description:
 * Any long (over1 yard), light thrusting sword.
 */
public class Rapier extends FencingMeleeWeapon {
    public Rapier(Character character) {
        super(character, "Rapier");
        try {
            setDefault("Broadsword", -4);
        } catch (UnknownSkillDefaultSkillException e) {
            e.printStackTrace();
        }
    }
}
