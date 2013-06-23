package net.mortiy.gurps.rules.combat.exceptions;

/**
* Created with IntelliJ IDEA.
* User: oleksandr.sidko
* Date: 23.06.13
* Time: 12:07
* To change this template use File | Settings | File Templates.
*/
public class ImpossibleManeuverException extends Exception {
    public ImpossibleManeuverException(String format, Object... args) {
        super(String.format(format, args));    //To change body of overridden methods use File | Settings | File Templates.
    }
}
