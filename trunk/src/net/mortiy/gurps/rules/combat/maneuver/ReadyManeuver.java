package net.mortiy.gurps.rules.combat.maneuver;

import net.mortiy.gurps.Log;
import net.mortiy.gurps.rules.combat.*;
import net.mortiy.gurps.rules.combat.exceptions.ImpossibleManeuverException;
import net.mortiy.gurps.rules.combat.exceptions.IsNotReadyException;
import net.mortiy.gurps.rules.equipment.weapon.Weapon;
import net.mortiy.gurps.rules.traits.Trait;


public class ReadyManeuver extends Maneuver {
    Preparable preparable;

    public ReadyManeuver(Preparable preparable) {
        super(ManeuverType.Ready);
        this.preparable = preparable;
    }

    public Preparable getPreparable() {
        return preparable;
    }

    @Override
    public ManeuverResult resolve(Fighter fighter) throws ImpossibleManeuverException, IsNotReadyException {

        if(preparable instanceof Weapon){
            fighter.getReadyList().add(preparable);
            Log.i("Ready Maneuver", "'%s' prepared '%s'", fighter.getCharacter().getName(), ((Weapon) preparable).getName());
        }
        else if(preparable instanceof Trait){

        }
        return new ManeuverResult(ManeuverResult.Status.Success);
    }
}
