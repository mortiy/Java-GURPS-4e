package net.mortiy.gurps.rules.traits;

/**
 * You can apply limitations to almost any trait (although as with enhancements, skills are normally off-limits).
 * When you apply a limitation to a disadvantage, you reduce its value as a disadvantage;
 * e.g., a -10% limitation on a -25-point disadvantage would
 * make it a -22.5-point trait, which rounds to -22 points.
 * Limited disadvantages are worth fewer points because they affect you under more
 * restricted circumstances.
 * Remember that no matter how many limitations you take, you cannot
 * reduce the cost of a trait by more than 80%.
 * That is, when totaling modifiers, treat net modifiers below -80% as -80%.
 */
public class TraitLimitation {
    String name;
    int percent;

    public TraitLimitation(String name, int percent) {
        this.name = name;
        this.percent = percent;
    }
}
