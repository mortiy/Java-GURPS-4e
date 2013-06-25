package net.mortiy.gurps.rules.combat.maneuver;

import net.mortiy.gurps.rules.combat.Fighter;
import net.mortiy.gurps.rules.combat.Maneuver;
import net.mortiy.gurps.rules.combat.ManeuverResult;
import net.mortiy.gurps.rules.combat.ManeuverType;
import net.mortiy.gurps.rules.combat.exceptions.ImpossibleManeuverException;
import net.mortiy.gurps.rules.combat.exceptions.IsNotReadyException;

/**
 * Created with IntelliJ IDEA.
 * User: oleksandr.sidko
 * Date: 10.02.13
 * Time: 19:29
 * To change this template use File | Settings | File Templates.
 */
public class MoveManeuver extends Maneuver {
    public MoveManeuver() {
        super(ManeuverType.Move);
    }

    @Override
    public ManeuverResult resolve(Fighter fighter) throws ImpossibleManeuverException, IsNotReadyException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
