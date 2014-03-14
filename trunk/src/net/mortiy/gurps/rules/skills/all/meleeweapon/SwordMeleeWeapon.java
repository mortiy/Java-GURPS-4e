package net.mortiy.gurps.rules.skills.all.meleeweapon;

import net.mortiy.gurps.rules.Individual;
import net.mortiy.gurps.rules.skills.all.MeleeWeapon;

/**
 * Swords
 * ===================
 * Description:
 * A sword is a rigid, hilted blade with
 * a thrusting point, cutting edge, or both.
 * All swords are balanced, and can attack
 * and parry without becoming unready.
 */
public class SwordMeleeWeapon extends MeleeWeapon {

    public SwordMeleeWeapon(Individual individual, String name) {
        this(individual, name, Difficulty.Average);
    }

    public SwordMeleeWeapon(Individual individual, String name, Difficulty difficulty) {
        super(individual, name, difficulty);
    }
}
