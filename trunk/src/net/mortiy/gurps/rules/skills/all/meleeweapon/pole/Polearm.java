package net.mortiy.gurps.rules.skills.all.meleeweapon.pole;

import net.mortiy.gurps.rules.attributes.Attribute;
import net.mortiy.gurps.rules.skills.Skill;
import net.mortiy.gurps.rules.Character;
import net.mortiy.gurps.rules.skills.all.meleeweapon.PoleMeleeWeapon;

/**
 * Polearm
 * ========================
 * Description:
 * Any very long (at least 2 yards), unbalanced pole
 * weapon with a heavy striking head,
 * including the glaive, halberd, poleaxe, and
 * countless others. Polearms become
 * unready after an attack, but not after a
 * parry.
 */
public class Polearm extends PoleMeleeWeapon {
    public Polearm(Character character) {
        super(character, "Polearm");
        try {
            setDefault("Spear", -4);
            setDefault("Staff", -4);
            setDefault("Two-Handed Axe/Mace", -4);
        } catch (UnknownSkillDefaultSkillException e) {
            e.printStackTrace();
        }
    }
}
