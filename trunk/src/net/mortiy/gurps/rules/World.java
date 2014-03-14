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
    TechLevel techLevel;
    private List<Individual> individuals = new ArrayList<Individual>();
    private static Time time = Time.getInstance();

    public World(TechLevel techLevel) {
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

    public void addCharacter(Individual individual){
        // First of all, clear characters equipment:
        Equipment equipment = individual.getEquipment();
        equipment.clear();

        // Determine and set starting wealth:
        float wealthMultiplier = 1f;
        if(individual.hasTrait("Wealth")){
            wealthMultiplier = ((Wealth) individual.getTrait("Wealth")).getWealthMultiplier();
        }
        int startingWealth = Math.round(techLevel.getStartingWealth() * wealthMultiplier);
        // Put corresponding amount of money into his equipment
        individual.getEquipment().putItem(new Money(), startingWealth);

        individuals.add(individual);
    }

    // endregion

}
