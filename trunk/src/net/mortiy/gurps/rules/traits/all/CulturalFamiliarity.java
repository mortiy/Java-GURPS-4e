package net.mortiy.gurps.rules.traits.all;

import net.mortiy.gurps.rules.*;
import net.mortiy.gurps.rules.Character;
import net.mortiy.gurps.rules.traits.Advantage;
import net.mortiy.gurps.rules.traits.VariableTrait;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: oleksandr.sidko
 * Date: 11.12.12
 * Time: 2:02
 * To change this template use File | Settings | File Templates.
 */
public class CulturalFamiliarity extends VariableTrait implements Advantage {
    public static enum CultureType {
        Homeline,
        Alien
    }

    public static enum Culture {
        EastAsian,
        Muslim,
        Western
    }

    private Map<String, CultureType> cultures = new HashMap<String, CultureType>();

    int[] costs = new int[]{1, 2};

    public CulturalFamiliarity(Character character) {
        super(character, "Cultural Familiarity");
    }

    public void learnCulture(String culture, CultureType cultureType) {
        cultures.put(culture, cultureType);
    }

    public boolean isFamiliary(Culture culture) {
        return cultures.containsKey(culture);
    }

    @Override
    public int getRawCost() {
        int totalCost = 0;

        for (CultureType cultureType : cultures.values()) {
            totalCost += costs[cultureType.ordinal()];
        }
        return totalCost;
    }
}
