package net.mortiy.gurps.rules.traits;

import net.mortiy.gurps.rules.Individual;

/**
 * This is kind of traits which have several levels and may have different costs
 */
public class VariableTrait extends Trait {

    public enum Level implements ITraitLevel {
        Disabled;

        @Override
        public int getCost() {
            return 0;  //To change body of implemented methods use File | Settings | File Templates.
        }
    }

    protected interface ITraitLevel {
        int ordinal();
        int getCost();
    }

    protected ITraitLevel currentLevel;

    public VariableTrait(Individual individual, String name) {
        super(individual, name, CostType.Variable, 0);
    }

    public ITraitLevel getLevel() {
        ITraitLevel level = currentLevel;
        if (individual.hasModifier(this)) {
            int levelIndex = level.ordinal();
            ITraitLevel levels[] = getLevels();
            int traitModifier = (int) individual.getTotalModifier(this);
            levelIndex += traitModifier;
            if (levelIndex < 0 || levelIndex >= levels.length) {
                level = levelIndex < 0 ? Level.Disabled : levels[levels.length - 1];
            } else {
                level = levels[levelIndex];
            }
        }

        return level;
    }

    public ITraitLevel[] getLevels(){
        return currentLevel.getClass().getEnumConstants();
    }

    @Override
    public int getRawCost() {
        return currentLevel.getCost();
    }

    public int getCost(ITraitLevel level) {
        return level.getCost();
    }


    public void changeLevel(ITraitLevel level) {
        this.currentLevel = level;
        onLevelChange(this.currentLevel);
    }

    public void onLevelChange(ITraitLevel level) {

    }
}
