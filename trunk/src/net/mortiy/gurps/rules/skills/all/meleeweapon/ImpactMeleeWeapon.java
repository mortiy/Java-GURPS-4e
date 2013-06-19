package net.mortiy.gurps.rules.skills.all.meleeweapon;

import net.mortiy.gurps.rules.*;
import net.mortiy.gurps.rules.Character;
import net.mortiy.gurps.rules.skills.all.MeleeWeapon;

/**
 * Impact Weapon
 * =========================
 * Description:
 * An impact weapon is any rigid,
 * unbalanced weapon with most of its
 * mass concentrated in the head. Such a
 * weapon cannot parry if you have
 * already attacked with it on your turn.
 * The skills in this category default to
 * one another at -3.
 */
public class ImpactMeleeWeapon extends MeleeWeapon {
    public ImpactMeleeWeapon(Character character, String name) {
        super(character, name, Difficulty.Average);
    }
}
