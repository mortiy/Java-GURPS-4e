package net.mortiy.gurps.rules.spells.classes;

import net.mortiy.gurps.rules.spells.Spell;

/**
 * Special Spell
 * ========================
 * These spells follow special rules
 given in the spell description.
 */
public abstract class SpecialSpell extends Spell {
    protected SpecialSpell(String name, int duration, int cost, int timeToCast) {
        super(name, duration, cost, timeToCast);
    }
}
