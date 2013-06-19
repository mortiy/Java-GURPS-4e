package net.mortiy.gurps.rules.combat;

/**
 * Maneuver that character can perform during combat
 */
public class Maneuver {
    private Fighter fighter;
    protected ManeuverType type;

    public Maneuver(Fighter fighter, ManeuverType type) {
        this.fighter = fighter;
        this.type = type;
        fighter.setManeuver(this);
    }

    public ManeuverType getType() {
        return type;
    }

    public Fighter getFighter() {
        return fighter;
    }
}
