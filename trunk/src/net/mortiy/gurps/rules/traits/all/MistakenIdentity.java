package net.mortiy.gurps.rules.traits.all;

import net.mortiy.gurps.rules.Individual;
import net.mortiy.gurps.rules.traits.Disadvantage;
import net.mortiy.gurps.rules.traits.FixedTrait;

/**
 * Mistaken Identity
 * ======================================
 * You are often mistaken for someone else. Your “double’s” allies
 * approach you and tell you things you
 * don’t want to know, and his acquaintances will treat you in strange and
 * irritating ways. His enemies are after you, too! You might eventually get
 * things straightened out, but not without some effort.
 * If every member of your race looks
 * the same, your race qualifies for a
 * bizarre feature (see  Features and
 * Taboo Traits, p. 261), but you do not
 * have Mistaken Identity.
 */
public class MistakenIdentity extends FixedTrait implements Disadvantage {
    public MistakenIdentity(Individual individual) {
        super(individual, "Mistaken Identity", -5);
    }
}
