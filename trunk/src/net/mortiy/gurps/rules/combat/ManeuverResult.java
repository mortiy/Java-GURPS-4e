package net.mortiy.gurps.rules.combat;

public class ManeuverResult {

    enum Status {
        Success,
        Failure
    }

    Status status;

    public ManeuverResult(Status status) {
        this.status = status;
    }
}
