package net.mortiy.gurps.rules.character;


/**
 * Hit Location
 * ===========================
 * When you strike at an enemy, you
 * can usually choose what part of his
 * body to attack. Some body parts, or
 * “hit locations,” are harder than others
 * to hit in a fight; some are more (or
 * less) vulnerable to specific damage
 * types. There are a few exceptions:
 */
public enum BodyLocation {
    Torso(0),
    Vitals(-3),
    Skull(-7),
    Eye(-9),
    Face(-5),
    Neck(-5),
    Groin(-3),
    LeftArm(-2),
    RightArm(-2),
    LeftLeg(-2),
    RightLeg(-2),
    Hand(-4),
    Feet(-4);

    private int modifier;

    private BodyLocation(int modifier) {
        this.modifier = modifier;
    }

    public int getModifier() {
        return modifier;
    }
}
