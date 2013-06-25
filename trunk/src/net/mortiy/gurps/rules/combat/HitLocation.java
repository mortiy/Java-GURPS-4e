package net.mortiy.gurps.rules.combat;


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
public enum HitLocation {
    Torso(0),
    Vitals(-3),
    Skull(-7),
    Eye(-9),
    Face(-5),
    Neck(-5),
    Groin(-3),
    Arm(-2),
    Leg(-2),
    Hand(-4),
    Feet(-4);

    private int modifier;

    private HitLocation(int modifier) {
        this.modifier = modifier;
    }

    public int getModifier() {
        return modifier;
    }
}
