package net.mortiy.gurps.rules;

import net.mortiy.gurps.rules.equipment.Equipment;
import net.mortiy.gurps.rules.equipment.all.Money;
import net.mortiy.gurps.rules.traits.all.Wealth;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: oleksandr.sidko
 * Date: 11.12.12
 * Time: 1:15
 * To change this template use File | Settings | File Templates.
 */
public class World {
    TechLevel.Level techLevel;
    private List<Character> characters = new ArrayList<Character>();
    private static Time time = Time.getInstance();

    public World(TechLevel.Level techLevel) {
        this.techLevel = techLevel;
    }

    public static Time getTime() {
        return time;
    }

    public static enum Language {
        Anglish,
        English,
        Ukrainian,
        French,
    }

    // region Characters

    public void addCharacter(Character character){
        // First of all, clear characters equipment:
        Equipment equipment = character.getEquipment();
        equipment.clear();

        // Determine and set starting wealth:
        float wealthMultiplier = 1f;
        if(character.hasTrait("Wealth")){
            wealthMultiplier = ((Wealth)character.getTrait("Wealth")).getWealthMultiplier();
        }
        int startingWealth = Math.round(TechLevel.getStartingWealth(techLevel) * wealthMultiplier);
        // Put corresponding amount of money into his equipment
        character.getEquipment().putItem(new Money(), startingWealth);

        characters.add(character);
    }

    // endregion

}
