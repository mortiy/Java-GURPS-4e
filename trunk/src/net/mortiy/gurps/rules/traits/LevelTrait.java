package net.mortiy.gurps.rules.traits;

import net.mortiy.gurps.rules.Individual;

/**
 * Represents levelable trait.
 * Those which have some set of incrementing/decrementing levels.
 */
public class LevelTrait extends Trait {
    /**
     * Defines maximum level for this trait.
     * Value -1 means there is no limit.
     */
    protected int maxLevel = -1;
    /**
     * Defines minimum level for this trait.
     * Default is 0.
     */
    protected int minLevel = 0;
    /**
     * Current trait level
     */
    protected int currentLevel = 0;


    /**
     * Create new levelable trait with variable level cost
     *
     * @param individual Character
     * @param name      Trait name
     */
    public LevelTrait(Individual individual, String name) {
        this(individual, name, 0);
    }

    /**
     * Create new levelable trait with fixed level cost
     *
     * @param individual Character
     * @param name      Trait name
     * @param levelCost Level cost
     */
    public LevelTrait(Individual individual, String name, int levelCost) {
        super(individual, name, CostType.Level, levelCost);
    }

    /**
     * Create new levelable trait with fixed level cost and specifier maximum level
     *
     * @param individual Character
     * @param name      Trait name
     * @param levelCost Level cost
     * @param minLevel  Minimum possible trait's level
     * @param maxLevel  Maximum possible trait's level
     */
    public LevelTrait(Individual individual, String name, int levelCost, int minLevel, int maxLevel) {
        this(individual, name, levelCost);
        this.minLevel = minLevel;
        this.maxLevel = maxLevel;
    }

    /**
     * Create new levelable trait with fixed level cost and specifier maximum level
     *
     * @param individual Character
     * @param name      Trait name
     * @param levelCost Level cost
     * @param maxLevel  Maximum possible trait's level
     */
    public LevelTrait(Individual individual, String name, int levelCost, int maxLevel) {
        this(individual, name, levelCost, 0, maxLevel);
    }


    public void increaseLevel() {
        increaseLevel(1);
    }

    public void increaseLevel(int modifier) {
        if (maxLevel <= 0 || (currentLevel + modifier) <= maxLevel) {
            currentLevel += modifier;
            onLevelChange(currentLevel);
        }
    }

    public void decreaseLevel() {
        decreaseLevel(1);
    }

    public void decreaseLevel(int modifier) {
        if ((currentLevel - modifier) >= minLevel) {
            currentLevel -= modifier;
            onLevelChange(currentLevel);
        }
    }

    public int getCurrentLevel() {
        return currentLevel;
    }

    protected int getLevelCost() {
        return cost;
    }

    @Override
    public int getRawCost() {
        return currentLevel * getLevelCost();
    }

    public void onLevelChange(int currentLevel) {
    }
}
