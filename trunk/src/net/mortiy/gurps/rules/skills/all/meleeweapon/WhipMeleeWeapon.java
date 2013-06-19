package net.mortiy.gurps.rules.skills.all.meleeweapon;

import net.mortiy.gurps.rules.*;
import net.mortiy.gurps.rules.skills.all.MeleeWeapon;

/**
 * Whip
 * ========================
 * Description:
 * A whip is a flexible weapon made
 * from a length of chain, leather, wire,
 * etc. A whip can be up to seven yards
 * long – but note that a whip two yards
 * or more in length cannot strike at one
 * yard or closer, and is slow to ready
 * after an attack. A whip tends to wind
 * around its target, making it an excellent
 * disarming and entangling
 * weapon. However, a whip’s lack of
 * rigidity makes it a poor parrying
 * weapon. For details, see Special Melee
 * Weapon Rules (p. 404).
 * The skills in this category default to
 * one another at -3.
 */
public class WhipMeleeWeapon extends MeleeWeapon {
    public WhipMeleeWeapon(net.mortiy.gurps.rules.Character character, String name, Difficulty difficulty) {
        super(character, name, difficulty);
    }
}
