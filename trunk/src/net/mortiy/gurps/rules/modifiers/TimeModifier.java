package net.mortiy.gurps.rules.modifiers;


import net.mortiy.gurps.rules.Individual;
import net.mortiy.gurps.rules.Time;
import net.mortiy.gurps.rules.World;

import java.util.TimerTask;

/**
 * Used to time-lapse modifier, which disappear
 */
public class TimeModifier extends Modifier {
    private TimerTask timerTask;

    public TimeModifier(Individual individual, Modifier.IModifiable modifiedEntity, float modifierValue, float timeInSeconds) {
        this(individual, modifiedEntity, modifierValue, Math.round(timeInSeconds * 1000));
    }

    public TimeModifier(Individual individual, Modifier.IModifiable modifiedEntity, float modifierValue, long timeInMilliseconds) {
        super(individual, modifiedEntity, new SummandModifier(modifierValue));
        timerTask = World.getTime().subscribeForTimeEvent(new Time.TimeEventListener() {
            @Override
            public void run() {
                detach();
            }
        }, timeInMilliseconds);
    }

    public TimerTask getTimerTask(){
        return  timerTask;
    }

    @Override
    public void detach() {
        super.detach();
        timerTask.cancel();
    }

}
