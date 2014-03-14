package net.mortiy.gurps.rules;


import net.mortiy.gurps.rules.modifiers.Modifier;
import net.mortiy.gurps.rules.modifiers.SummandModifier;

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
        super.detach();    //To change body of overridden methods use File | Settings | File Templates.
        timerTask.cancel();
    }

}
