package net.mortiy.gurps.rules.spells.classes;

import net.mortiy.gurps.rules.spells.Spell;

/**
 * Melee Spells
 * ===============================
 * Melee spells “charge” your hand or
 magic staff (see box) with harmful
 energies that affect the first target you
 strike. These spells require two skill
 rolls: a roll against spell skill to cast
 the spell, and a normal melee attack
 roll to hit your target with your hand
 or staff.
 */
public abstract class MeleeSpell extends Spell {
    protected MeleeSpell(String name, int duration, int cost, int timeToCast) {
        super(name, duration, cost, timeToCast);
    }
}
