package net.mortiy.gurps.rules.hazards.afflictions;

import net.mortiy.gurps.rules.*;
import net.mortiy.gurps.rules.attributes.Attribute;
import net.mortiy.gurps.rules.hazards.Affliction;
import net.mortiy.gurps.rules.hazards.Hazard;
import net.mortiy.gurps.rules.modifiers.SummandModifier;
import net.mortiy.gurps.rules.table.Rolls;
import net.mortiy.gurps.rules.table.rolls.SuccessRoll;

/**
 * You have -2 to all attribute and skill rolls, and -1 to active defenses.
 * As well, roll vs. HT after you eat, are exposed to a foul odor, fail a
 * Fright Check, or are stunned, and every hour in free fall or in any situation
 * where you might suffer motion sickness.
 * A rich meal in the past hour gives -2; anti-nausea remedies give +2.
 * On a failure, you vomit for (25 - HT) seconds – treat as Retching, below.
 * (Source: p.428)
 */
public class Nauseated extends Affliction {
    protected Nauseated(Individual character) {
        super(character);
    }

    @Override
    public int affect() {

        addModifier(Rolls.AttributeRoll, new SummandModifier(-2));
        addModifier(Rolls.SkillRoll, new SummandModifier(-2));
        addModifier(Rolls.DefenseRoll, new SummandModifier(-1));

        Event.on(c, Action.Eat, new Event.Listener() {
            @Override
            public void emit() {
                rollForVomit();
            }
        });

        return 0;
    }

    public void rollForVomit() {
        int healthLevel = c.getBasicAttribute(Attribute.Health).getLevel();
        switch (new SuccessRoll(healthLevel).roll().getRollResult()) {
            case Failure:
            case CriticalFailure:
                int vomitDuration = 25 - healthLevel; // in seconds
                if (vomitDuration > 0) {
                    final Hazard retching = new Retching(c);
                    World.getTime().subscribeForTimeEvent(new Time.TimeEventListener() {
                        @Override
                        public void run() {
                            retching.remove();
                        }
                    }, vomitDuration * 1000);
                    break;
                }
        }
    }
}
