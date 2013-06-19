package net.mortiy.gurps.rules.exceptions;

/**
* Created with IntelliJ IDEA.
* User: oleksandr.sidko
* Date: 10.12.12
* Time: 1:52
* To change this template use File | Settings | File Templates.
*/
public class NotEnoughCharacterPointsException extends Exception {
    public NotEnoughCharacterPointsException(String detailMessage) {
        super(detailMessage);    //To change body of overridden methods use File | Settings | File Templates.
    }
}
