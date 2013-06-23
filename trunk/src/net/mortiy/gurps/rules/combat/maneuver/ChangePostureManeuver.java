package net.mortiy.gurps.rules.combat.maneuver;

import net.mortiy.gurps.rules.Character;
import net.mortiy.gurps.rules.combat.Fighter;
import net.mortiy.gurps.rules.combat.Maneuver;
import net.mortiy.gurps.rules.combat.ManeuverType;

/**
 * Created with IntelliJ IDEA.
 * User: oleksandr.sidko
 * Date: 10.02.13
 * Time: 19:29
 * To change this template use File | Settings | File Templates.
 */
public class ChangePostureManeuver extends Maneuver {
    Character.Posture posture;
    public ChangePostureManeuver(Character.Posture posture) {
        super(ManeuverType.ChangePosture);
        this.posture = posture;
    }

    public Character.Posture getPosture() {
        return posture;
    }
}
