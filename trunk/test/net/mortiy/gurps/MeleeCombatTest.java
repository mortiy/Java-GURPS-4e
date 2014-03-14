package net.mortiy.gurps;

import junit.framework.TestCase;
import net.mortiy.gurps.rules.Individual;
import net.mortiy.gurps.rules.attributes.Attribute;
import net.mortiy.gurps.rules.combat.Combat;
import net.mortiy.gurps.rules.combat.CombatManager;
import net.mortiy.gurps.rules.combat.Fighter;
import net.mortiy.gurps.rules.combat.exceptions.FighterHasNoManueverException;
import net.mortiy.gurps.rules.combat.exceptions.ImpossibleManeuverException;
import net.mortiy.gurps.rules.combat.exceptions.IsNotReadyException;
import net.mortiy.gurps.rules.combat.exceptions.RoundIsOverException;
import net.mortiy.gurps.rules.combat.maneuver.AllOutAtackManeuver;
import net.mortiy.gurps.rules.combat.maneuver.AttackManeuver;
import net.mortiy.gurps.rules.combat.maneuver.ReadyManeuver;
import net.mortiy.gurps.rules.combat.maneuver.WaitManeuver;
import net.mortiy.gurps.rules.equipment.Equipment;
import net.mortiy.gurps.rules.equipment.armor.Boots;
import net.mortiy.gurps.rules.equipment.armor.HeavyLeatherLeggings;
import net.mortiy.gurps.rules.equipment.armor.HeavyLeatherSleeves;
import net.mortiy.gurps.rules.equipment.armor.LeatherArmor;
import net.mortiy.gurps.rules.equipment.weapon.all.MaceWeapon;
import net.mortiy.gurps.rules.equipment.weapon.all.ThrustingBroadswordWeapon;
import net.mortiy.gurps.rules.exceptions.NotEnoughCharacterPointsException;
import net.mortiy.gurps.rules.individual.Body;
import net.mortiy.gurps.rules.skills.all.meleeweapon.impact.AxeMace;
import net.mortiy.gurps.rules.skills.all.meleeweapon.sword.Broadsword;

import java.util.List;

public class MeleeCombatTest extends TestCase {

    public void testCombat() {
        Individual arthurGreen = new Individual(100);
        try {
            arthurGreen.setBasicAttribute(Attribute.Strength, 12);
            arthurGreen.setBasicAttribute(Attribute.Dexterity, 10);
            arthurGreen.setBasicAttribute(Attribute.Intelligence, 10);
            arthurGreen.setBasicAttribute(Attribute.Health, 12);
        } catch (NotEnoughCharacterPointsException e) {
            e.printStackTrace();
        }

        assertEquals("Arthur's basic speed", 5.5f, arthurGreen.getBasicSpeed().getValue());
        assertEquals("Arthur's dodge", 8.0f, arthurGreen.getDodge().getValue());
        assertEquals("Arthur's basic move", 5f, arthurGreen.getBasicMove().getValue());

        // Arthur's equipment:
        Equipment arthursEquipment = arthurGreen.getEquipment();
        arthursEquipment.putAndEquipItem(new LeatherArmor(), Body.Part.Torso);
        arthursEquipment.putAndEquipItem(new HeavyLeatherSleeves(), Body.Part.Arms);
        arthursEquipment.putAndEquipItem(new HeavyLeatherLeggings(), Body.Part.Legs);
        arthursEquipment.putAndEquipItem(new Boots(), Body.Part.Feet);
        arthursEquipment.putAndEquipItem(new ThrustingBroadswordWeapon(), Body.Part.RightHand);
        assertEquals("Arthur's equipment weight", 22.0f, arthursEquipment.getTotalWeight());

        // Arhutr's skills:
        Broadsword broadswordSkill = new Broadsword(arthurGreen);
        broadswordSkill.setLevel(14);
        assertEquals("Arthur's Broadsword skill level", 14, broadswordSkill.getSkillLevel());


        Individual zachRed = new Individual(100);

        try {
            zachRed.setBasicAttribute(Attribute.Strength, 12);
            zachRed.setBasicAttribute(Attribute.Dexterity, 12);
            zachRed.setBasicAttribute(Attribute.Intelligence, 10);
            zachRed.setBasicAttribute(Attribute.Health, 10);
        } catch (NotEnoughCharacterPointsException e) {
            e.printStackTrace();
        }

        assertEquals("Zach's basic speed", 5.5f, arthurGreen.getBasicSpeed().getValue());
        assertEquals("Zach's dodge", 8.0f, arthurGreen.getDodge().getValue());
        assertEquals("Zach's basic move", 5f, arthurGreen.getBasicMove().getValue());

        // Arthur's equipment:
        Equipment zachsEquipment = zachRed.getEquipment();
        zachsEquipment.putAndEquipItem(new LeatherArmor(), Body.Part.Torso);
        zachsEquipment.putAndEquipItem(new HeavyLeatherSleeves(), Body.Part.Arms);
        zachsEquipment.putAndEquipItem(new HeavyLeatherLeggings(), Body.Part.Legs);
        zachsEquipment.putAndEquipItem(new Boots(), Body.Part.Feet);
        zachsEquipment.putAndEquipItem(new MaceWeapon(), Body.Part.RightHand);
        assertEquals("Zach's equipment weight", 24.0f, zachsEquipment.getTotalWeight());

        // Arhutr's skills:
        AxeMace axeMaceSkill = new AxeMace(zachRed);
        axeMaceSkill.setLevel(16);
        assertEquals("Zach's Ace/Mace skill level", 16, axeMaceSkill.getSkillLevel());

        // Let the fight begin!
        Combat combat = new Combat(arthurGreen, zachRed);
        List<Fighter> fighters = combat.getFighters();

        Fighter arthurFighter = fighters.get(0);
        Fighter zachFighter = fighters.get(1);

        CombatManager combatManager = new CombatManager(combat);


        try {
            // Prepare weapons:
            zachFighter.setNextManeuver(new ReadyManeuver(zachFighter.getActiveWeapon()));
            combatManager.turn();
            arthurFighter.setNextManeuver(new ReadyManeuver(arthurFighter.getActiveWeapon()));
            combatManager.turn();

            combatManager.startNewRound();

            zachFighter.setNextManeuver(
                    new WaitManeuver(new AttackManeuver(arthurFighter, AttackManeuver.Type.Melee, zachFighter.getActiveWeapon()))
            );
            combatManager.turn();
            arthurFighter.setNextManeuver(
                    new AllOutAtackManeuver(zachFighter, AttackManeuver.Type.Melee, arthurFighter.getActiveWeapon(), AllOutAtackManeuver.MeleeOption.Determined)
            );
            combatManager.turn();

        } catch (FighterHasNoManueverException e) {
            e.printStackTrace();
        } catch (ImpossibleManeuverException e) {
            e.printStackTrace();
        } catch (RoundIsOverException e) {
            e.printStackTrace();
        } catch (IsNotReadyException e) {
            e.printStackTrace();
        }
    }
}
