package net.mortiy.gurps.rules.combat.maneuver;

import net.mortiy.gurps.Log;
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
 * Time: 19:21
 * To change this template use File | Settings | File Templates.
 */
public class DoNothingManeuver extends Maneuver {
    public DoNothingManeuver() {
        super(ManeuverType.DoNothing);
    }

    @Override
    public ManeuverResult resolve(Fighter fighter) throws ImpossibleManeuverException, IsNotReadyException {
        Log.i("Do Nothing Maneuver", "%s doesn nothing.", fighter.getIndividual().getName());
        return new ManeuverResult(ManeuverResult.Status.Success);
    }
}
