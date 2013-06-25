package net.mortiy.gurps.rules.combat;

/**
 * Maneuver that character can perform during combat
 */
public abstract class Maneuver implements ManeuverResolver {
    protected ManeuverType type;

    public Maneuver(ManeuverType type) {
        this.type = type;
    }

    public ManeuverType getType() {
        return type;
    }
}
