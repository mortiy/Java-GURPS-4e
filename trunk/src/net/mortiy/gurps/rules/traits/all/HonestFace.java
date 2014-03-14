package net.mortiy.gurps.rules.traits.all;

import net.mortiy.gurps.rules.Individual;
import net.mortiy.gurps.rules.traits.Perk;

/**
 * HonestFace
 * ============================
 * You simply look honest, reliable, or generally harmless.
 * This has nothing to do with your reputation among
 * those who know you, or how virtuous you really are!
 * People who don’t know you will tend to pick you as the one to
 * confide in, or not to pick you if they are looking for a potential criminal or troublemaker.
 * You won’t be spotchecked by customs agents and the
 * like unless they have another reason to
 * suspect you, or unless they are truly
 * choosing at random.
 * You have a +1 to trained Acting skill for the sole purpose of “acting innocent.”
 */
public class HonestFace extends Perk {

    public HonestFace(Individual individual) {
        super(individual, "Honest Face");
    }
}
