package net.mortiy.gurps.rules.traits;

import net.mortiy.gurps.rules.Character;

/**
 * This is kind of traits which have several levels and may have different costs
 */
public class VariableTrait extends Trait {

    protected interface ITraitLevel {
        int ordinal();
    }

    protected ITraitLevel currentLevel;

    protected int levelsCost[];

    public VariableTrait(Character character, String name) {
        super(character, name, CostType.Variable, 0);
    }

    public ITraitLevel getLevel() {
        return currentLevel;
    }

    @Override
    public int getRawCost() {
        return levelsCost[((Enum) currentLevel).ordinal()];
    }

    public int getCost(ITraitLevel level) {
        return levelsCost[((Enum) level).ordinal()];
    }


    public void changeLevel(ITraitLevel level) {
        this.currentLevel = level;
        onLevelChange(this.currentLevel);
    }

    public void onLevelChange(ITraitLevel level){

    }
}
