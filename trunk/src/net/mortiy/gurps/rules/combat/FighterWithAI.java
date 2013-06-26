package net.mortiy.gurps.rules.combat;

import net.mortiy.gurps.rules.Character;
import net.mortiy.gurps.rules.combat.maneuver.DoNothingManeuver;

public class FighterWithAI extends Fighter {

    public FighterWithAI(Character character) {
        super(character);
    }

    @Override
    public Maneuver getNextManeuver() {
        // TODO: AI for best maneuver selecting
        return new DoNothingManeuver();
    }
}
