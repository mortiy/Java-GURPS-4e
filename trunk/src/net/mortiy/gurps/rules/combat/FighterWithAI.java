package net.mortiy.gurps.rules.combat;

import net.mortiy.gurps.rules.Individual;
import net.mortiy.gurps.rules.combat.maneuver.DoNothingManeuver;

public class FighterWithAI extends Fighter {

    public FighterWithAI(Individual individual) {
        super(individual);
    }

    @Override
    public Maneuver getNextManeuver() {
        // TODO: AI for best maneuver selecting
        return new DoNothingManeuver();
    }
}
