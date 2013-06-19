package net.mortiy.gurps.rules.spells.classes;

import net.mortiy.gurps.rules.spells.Spell;

/**
 * Blocking Spell
 * =============================
 * A Blocking spell is cast instantly as
 * a defense against either a physical
 * attack or another spell. It is the magical
 * equivalent of a block, parry, or
 * dodge (and often counts as one of
 * these defenses; see the spell description
 * for details). You may cast only one
 * Blocking spell per turn, no matter how
 * skilled you are. You cannot attempt a
 * Blocking spell against a critical hit.
 */
public abstract class BlockingSpell extends Spell {
    protected BlockingSpell(String name, int duration, int cost, int timeToCast) {
        super(name, duration, cost, timeToCast);
    }
}
