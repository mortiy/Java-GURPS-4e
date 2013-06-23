package net.mortiy.gurps.rules.combat;

/**
 * Maneuver that character can perform during combat
 */
public class Maneuver {
    protected ManeuverType type;

    public Maneuver(ManeuverType type) {
        this.type = type;
    }

    public ManeuverType getType() {
        return type;
    }
}
