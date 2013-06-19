package net.mortiy.gurps.rules.combat.maneuver;

import net.mortiy.gurps.rules.combat.Fighter;
import net.mortiy.gurps.rules.combat.Maneuver;
import net.mortiy.gurps.rules.combat.ManeuverType;

/**
 * Created with IntelliJ IDEA.
 * User: oleksandr.sidko
 * Date: 10.02.13
 * Time: 19:32
 * To change this template use File | Settings | File Templates.
 */
public class EvaluateManeuver extends Maneuver {
    public EvaluateManeuver(Fighter fighter) {
        super(fighter, ManeuverType.Evaluate);
    }
}
