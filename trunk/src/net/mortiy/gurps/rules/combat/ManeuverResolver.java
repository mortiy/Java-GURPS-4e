package net.mortiy.gurps.rules.combat;

import net.mortiy.gurps.rules.combat.exceptions.ImpossibleManeuverException;
import net.mortiy.gurps.rules.combat.exceptions.IsNotReadyException;

public interface ManeuverResolver {
    public ManeuverResult resolve(Fighter fighter) throws ImpossibleManeuverException, IsNotReadyException;
}
