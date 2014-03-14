package net.mortiy.gurps.rules.combat;

import net.mortiy.gurps.Log;
import net.mortiy.gurps.rules.Individual;
import net.mortiy.gurps.rules.attributes.Attribute;
import net.mortiy.gurps.rules.combat.exceptions.ImpossibleManeuverException;
import net.mortiy.gurps.rules.combat.exceptions.IsNotReadyException;
import net.mortiy.gurps.rules.combat.maneuver.*;

import java.util.*;

/**
 * Represents fight between several Characters
 */
public class Combat {

    private List<Fighter> fighters = new ArrayList<>();

    public Combat(Individual... fighters) {
        this(Arrays.asList(fighters));
    }

    public Combat(List<Individual> individuals) {

        /**
         * Determine turns order by character's Basic Speed:
         */
        Collections.sort(individuals, new Comparator<Individual>() {
            public int compare(Individual c1, Individual c2) {
                int result = c2.getBasicSpeed().compareTo(c1.getBasicSpeed());
                if (result == 0) {
                    return c2.getBasicAttribute(Attribute.Dexterity).compareTo(c1.getBasicAttribute(Attribute.Dexterity));
                }
                return result;
            }
        });

        /**
         * Initialize fighters and their starting maneuvers:
         */
        for (Individual individual : individuals) {
            Fighter fighter = new Fighter(individual);
            fighter.setNextManeuver(new DoNothingManeuver());
            this.fighters.add(fighter);
        }
    }

    public List<Fighter> getFighters() {
        return fighters;
    }

    public Fighter getFighter(int index) {
        return fighters.get(index);
    }

    /**
     * Removes character from the combat
     *
     * @param individual
     */
    public void leaveCombat(Individual individual) {
        for (Fighter fighter : fighters) {
            if (fighter.getIndividual() == individual) {
                leaveCombat(fighter);
            }
        }
    }

    public void leaveCombat(Fighter fighter) {
        fighters.remove(fighter);
    }

    /**
     * Resolver fighter's maneuver
     *
     * @param fighter
     * @throws net.mortiy.gurps.rules.combat.exceptions.ImpossibleManeuverException
     *
     */
    ManeuverResult resolveManeuver(Fighter fighter) throws ImpossibleManeuverException, IsNotReadyException {
        Maneuver maneuver = fighter.getNextManeuver();

        Individual fighterIndividual = fighter.getIndividual();
        String fighterName = fighterIndividual.getName();

        _log("'%s' performs <%s>", fighterName, maneuver.getType());

        return maneuver.resolve(fighter);
    }

    private void _log(String message, Object... args) {
        Log.i("Combat", String.format(message, args));
    }

}
