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
 * Time: 19:42
 * To change this template use File | Settings | File Templates.
 */
public class WaitManeuver extends Maneuver {
    Maneuver nextManeuver;
    public WaitManeuver(Maneuver nextManeuver) {
        super(ManeuverType.Wait);
        this.nextManeuver = nextManeuver;
    }

    public Maneuver getNextManeuver() {
        return nextManeuver;
    }

    @Override
    public ManeuverResult resolve(Fighter fighter) throws ImpossibleManeuverException, IsNotReadyException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
