package net.mortiy.gurps.rules.spells.classes;

import net.mortiy.gurps.rules.spells.Spell;

/**
 * Resisted Spell
 * ===================================
 * A spell of any type can also be
 * “Resisted.” A spell like this works automatically
 * only on a critical success. On
 * a regular success, your spell must
 * defeat the subject’s resistance to work.
 */
public abstract class ResistedSpell extends Spell {
    protected ResistedSpell(String name, int duration, int cost, int timeToCast) {
        super(name, duration, cost, timeToCast);
    }
}
