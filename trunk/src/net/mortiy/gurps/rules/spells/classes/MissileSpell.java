package net.mortiy.gurps.rules.spells.classes;

import net.mortiy.gurps.rules.spells.Spell;

/**
 * Missile Spell
 * =======================================
 * This class of spells encompasses
 * long-distance “projectile” or “bolt”
 * attacks, such as Fireball (p. 247) and
 * Lightning (p. 244). Missile spells
 * require two skill rolls: a roll against
 * spell skill to cast the spell, and a roll
 * against Innate Attack skill (p. 201) to
 * hit the target.
 */
public abstract class MissileSpell extends Spell {
    protected MissileSpell(String name, int duration, int cost, int timeToCast) {
        super(name, duration, cost, timeToCast);
    }
}
