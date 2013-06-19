package net.mortiy.gurps.rules.spells.all;

import net.mortiy.gurps.rules.spells.classes.AreaSpell;

/**
 * Purify Air | Area
 * This spell removes all impurities
 * from the air in its area of effect. It is
 * often used to neutralize the effects of
 * poisonous gas or vapors. Note that a
 * room full of smoke may safely be purified
 * one section at a time – but truly
 * deadly vapors must all be removed at
 * once, or some may escape.
 */
public class PurifyAir extends AreaSpell {
    protected PurifyAir(String name, int duration, int cost, int timeToCast) {
        super("Purify Air", 0, 1, 0);
    }
}
