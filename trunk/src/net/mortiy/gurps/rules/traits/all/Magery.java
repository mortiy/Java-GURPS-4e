package net.mortiy.gurps.rules.traits.all;

import net.mortiy.gurps.rules.Character;
import net.mortiy.gurps.rules.traits.Advantage;
import net.mortiy.gurps.rules.traits.LevelTrait;
import net.mortiy.gurps.rules.traits.categories.Mental;
import net.mortiy.gurps.rules.traits.categories.Supernatural;

/**
 * Magery
 * ====================
 * You are magically adept.
 * This advantage comes in levels.
 * You must purchase Magery 0 before buying higher levels of Magery.
 */
public class Magery extends LevelTrait implements Advantage, Mental, Supernatural {
    final int MAGERY_LEVEL_0 = 1;
    final int MAGERY_LEVEL_0_COST = 5;

    public Magery(Character character) {
        super(character, "Magery", 10);
    }

    @Override
    public int getCost() {
        return currentLevel > 0
                ? currentLevel == MAGERY_LEVEL_0
                ? MAGERY_LEVEL_0_COST
                : super.getCost() - 5 // -5 as exclusion for cost of Level 0 (which if 5 points)
                : 0;    //To change body of overridden methods use File | Settings | File Templates.
    }

    public int getMageryLevel() {
        return currentLevel - 1;
    }
}
