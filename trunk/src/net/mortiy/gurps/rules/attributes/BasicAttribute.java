package net.mortiy.gurps.rules.attributes;

import net.mortiy.gurps.rules.skills.interfaces.ISkillDefault;

/**
 * Four numbers called “attributes”
 * define your basic abilities: Strength
 * (ST), Dexterity (DX), Intelligence (IQ),
 * and Health (HT).
 * A score of 10 in any attribute is free,
 * and represents the human average.
 * Higher scores cost points: 10 points to
 * raise ST or HT by one level, 20 points
 * to raise DX or IQ by one level.
 * Similarly, scores lower than 10 have a
 * negative cost: -10 points per level for
 * ST or HT, -20 points per level for DX or
 * IQ. (Remember – negative point values
 * mean you get those points back to
 * spend on something else!)
 */
public class BasicAttribute extends CharacterAttribute implements ISkillDefault {

    public final static int DEFAULT_LEVEL = 10;

    public BasicAttribute() {
        this(DEFAULT_LEVEL);
    }

    public BasicAttribute(int level) {
        super(Attribute.Unknown);
        this.level = level;
    }

    public int getLevel(){
        return new Float(getValue()).intValue();
    }

    @Override
    public float getCost() {
        return (level - DEFAULT_LEVEL) * levelCost;
    }




}
