package net.mortiy.gurps.rules.combat.maneuver;

import net.mortiy.gurps.rules.combat.Maneuver;
import net.mortiy.gurps.rules.combat.ManeuverType;
import net.mortiy.gurps.rules.combat.Preparable;


public class ReadyManeuver extends Maneuver {
    Preparable preparable;
    public ReadyManeuver(Preparable preparable) {
        super(ManeuverType.Ready);
        this.preparable = preparable;
    }

    public Preparable getPreparable() {
        return preparable;
    }
}
