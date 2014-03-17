package net.mortiy.gurps.rules.attributes;

import net.mortiy.gurps.rules.modifiers.Modifier;

public class CharacterAttribute implements Modifier.IModifiable {
    protected Attribute attribute = Attribute.Unknown;
    protected float level = 0;
    protected int levelCost = 0;
    protected float levelIncrement = 1;

    public CharacterAttribute(Attribute attribute) {
        this.attribute = attribute;
    }

    public void decrease() {
        decrease(1);
    }

    public void increase() {
        increase(1);
    }

    public void increase(float modifier) {
        changeLevel(+modifier);
    }

    public void decrease(float modifier) {
        changeLevel(-modifier);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CharacterAttribute)) return false;

        CharacterAttribute that = (CharacterAttribute) o;

        if (attribute != that.attribute) return false;
        if (this.getValue() != that.getValue()) return false;

        return true;
    }

    private void changeLevel(float modifier) {
        level += modifier;
    }

    public float getValue() {
        return level;
    }

    public int getLevelCost() {
        return levelCost;
    }

    public float getLevelIncrement() {
        return levelIncrement;
    }

    public float getCost() {
        return Math.round((level / levelIncrement) * levelCost);
    }

    public int compareTo(CharacterAttribute attribute){
        return new Float(getValue()).compareTo(attribute.getValue());
    }
}
