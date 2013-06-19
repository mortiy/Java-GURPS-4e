package net.mortiy.gurps.rules.combat.maneuver;

import net.mortiy.gurps.rules.combat.Fighter;
import net.mortiy.gurps.rules.combat.Maneuver;
import net.mortiy.gurps.rules.combat.ManeuverType;

/**
 * Created with IntelliJ IDEA.
 * User: oleksandr.sidko
 * Date: 10.02.13
 * Time: 19:30
 * To change this template use File | Settings | File Templates.
 */
public class FeintManeuver extends Maneuver {
    public FeintManeuver(Fighter fighter) {
        super(fighter, ManeuverType.Feint);
    }
}