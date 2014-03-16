package net.mortiy.gurps.rules.hazards.afflictions;

import net.mortiy.gurps.rules.Individual;
import net.mortiy.gurps.rules.Time;
import net.mortiy.gurps.rules.hazards.Affliction;

/**
 * You are on the verge of falling asleep.
 * Make a Will roll every two hours you spend inactive.
 * On a failure, you fall asleep, and sleep until
 * you are awakened or get a full night’s sleep.
 * On a success, you have -2 to DX, IQ, and self-control rolls.
 */
public class Drowsy extends Affliction {
    protected Drowsy(Individual character) {
        super(character);
    }

    @Override
    public int affect() {
        //character.addTimeModifier(Time.TURN);
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
