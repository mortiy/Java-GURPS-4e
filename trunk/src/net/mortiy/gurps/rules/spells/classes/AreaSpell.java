package net.mortiy.gurps.rules.spells.classes;

import net.mortiy.gurps.rules.spells.Spell;

/**
 * Area Spell
 * ==============================
 * These spells affect an area rather
 * than an individual. They are cast on a
 * surface – floor, ground, etc. – and their
 * effects extend four yards (12 feet) up
 * from that surface. A few Area spells
 * work differently; see the individual
 * spell descriptions for details.
 */
public abstract class AreaSpell extends Spell {
    protected AreaSpell(String name, int duration, int cost, int timeToCast) {
        super(name, duration, cost, timeToCast);
    }
}
