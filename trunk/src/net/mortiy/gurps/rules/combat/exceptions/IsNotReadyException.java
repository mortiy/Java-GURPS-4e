package net.mortiy.gurps.rules.combat.exceptions;

/**
 * Created with IntelliJ IDEA.
 * User: oleksandr.sidko
 * Date: 24.06.13
 * Time: 17:35
 * To change this template use File | Settings | File Templates.
 */
public class IsNotReadyException extends Exception {
    public IsNotReadyException() {
    }

    public IsNotReadyException(String message) {
        super(message);
    }
}
