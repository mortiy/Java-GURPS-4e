package net.mortiy.gurps.rules.table;

/**
 * Defines how much 6d dice should be role and modifer
 * added to the roll result
 */
public class RollFormula {
    int diceQuantity;
    int modifier = 0;

    public RollFormula(int diceQuantity) {
        this(diceQuantity, 0);
    }

    public RollFormula(int diceQuantity, int modifier) {
        this.diceQuantity = diceQuantity;
        this.modifier = modifier;
    }

    public int getDiceQuantity() {
        return diceQuantity;
    }

    public int getModifier() {
        return modifier;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RollFormula)) return false;

        RollFormula that = (RollFormula) o;

        if (diceQuantity != that.diceQuantity) return false;
        if (modifier != that.modifier) return false;

        return true;
    }

    @Override
    public String toString() {
        return modifier != 0
                ? String.format("<%dd%+d>", diceQuantity, modifier)
                : String.format("<%dd>", diceQuantity);
    }
}
