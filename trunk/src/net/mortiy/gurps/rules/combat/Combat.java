package net.mortiy.gurps.rules.combat;

import net.mortiy.gurps.Log;
import net.mortiy.gurps.rules.Character;
import net.mortiy.gurps.rules.attributes.Attribute;
import net.mortiy.gurps.rules.combat.exceptions.ImpossibleManeuverException;
import net.mortiy.gurps.rules.combat.exceptions.IsNotReadyException;
import net.mortiy.gurps.rules.combat.maneuver.*;
import net.mortiy.gurps.rules.equipment.Item;
import net.mortiy.gurps.rules.equipment.weapon.MeleeWeapon;
import net.mortiy.gurps.rules.equipment.weapon.MusclePoweredMeleeWeapon;
import net.mortiy.gurps.rules.equipment.weapon.Weapon;
import net.mortiy.gurps.rules.equipment.weapon.statistics.MusclePoweredDamage;
import net.mortiy.gurps.rules.skills.Skill;
import net.mortiy.gurps.rules.table.DiceRoller;
import net.mortiy.gurps.rules.table.RollFormula;
import net.mortiy.gurps.rules.table.rolls.SuccessRoll;

import java.util.*;

/**
 * Represents fight between several Characters
 */
public class Combat {

    private List<Fighter> fighters = new ArrayList<>();

    public Combat(Character... fighters) {
        this(Arrays.asList(fighters));
    }

    public Combat(List<Character> characters) {

        /**
         * Determine turns order by character's Basic Speed:
         */
        Collections.sort(characters, new Comparator<Character>() {
            public int compare(Character c1, Character c2) {
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
        for (Character character : characters) {
            Fighter fighter = new Fighter(character);
            fighter.setManeuver(new DoNothingManeuver());
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
     * @param character
     */
    public void leaveCombat(Character character) {
        for (Fighter fighter : fighters) {
            if (fighter.getCharacter() == character) {
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
        Maneuver maneuver = fighter.getActiveManeuver();

        Character fighterCharacter = fighter.getCharacter();
        String fighterName = fighterCharacter.getName();

        _log("'%s' performs <%s>", fighterName, maneuver.getType());

        return maneuver.resolve(fighter);
    }

    private void _log(String message, Object... args) {
        Log.i("Combat", String.format(message, args));
    }

}
