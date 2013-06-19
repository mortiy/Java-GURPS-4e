package net.mortiy.gurps.rules.skills.all.meleeweapon;

import net.mortiy.gurps.rules.*;
import net.mortiy.gurps.rules.skills.all.MeleeWeapon;

/**
 * Flails
 * =====================
 * Description:
 * A flail is any flexible, unbalanced
 * weapon with its mass concentrated in
 * the head. Such a weapon cannot parry
 * if you have already attacked with it on
 * your turn. Because flails tend to wrap
 * around the target’s shield or weapon,
 * attempts to block them are at -2 and
 * attempts to parry them are at -4.
 * Fencing weapons and knives cannot
 * parry them at all! An unarmed fighter
 * can parry a flail, but at -4 in addition
 * to any penalty for parrying unarmed.
 * The skills in this category default to
 * one another at -3.
 */
public class FlailMeleeWeapon extends MeleeWeapon {
    public FlailMeleeWeapon(net.mortiy.gurps.rules.Character character, String name) {
        super(character, name, Difficulty.Hard);
    }
}
