package net.mortiy.gurps.rules.skills.all.meleeweapon;

import net.mortiy.gurps.rules.Character;
import net.mortiy.gurps.rules.skills.all.MeleeWeapon;

/**
 * <h1>Fencing Weapon</h1>
 * ========================<br/>
 * Description:
 * Fencing weapons are light, onehanded
 * weapons, usually hilted blades, optimized for parrying.
 * If you have a fencing weapon, you get an improved
 * retreating bonus when you parry – see Retreat (p. 377).
 * Furthermore, you have half the usual
 * penalty for parrying more than once
 * with the same hand
 */
public class FencingMeleeWeapon extends MeleeWeapon {
    public FencingMeleeWeapon(Character character, String name) {
        super(character, name, Difficulty.Average);
    }
}
