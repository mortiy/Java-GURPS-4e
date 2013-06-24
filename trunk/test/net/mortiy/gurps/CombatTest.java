package net.mortiy.gurps;

import junit.framework.TestCase;
import net.mortiy.gurps.rules.Character;
import net.mortiy.gurps.rules.combat.*;
import net.mortiy.gurps.rules.attributes.Attribute;
import net.mortiy.gurps.rules.character.Body;
import net.mortiy.gurps.rules.combat.maneuver.*;
import net.mortiy.gurps.rules.equipment.Equipment;
import net.mortiy.gurps.rules.equipment.ShieldItem;
import net.mortiy.gurps.rules.equipment.all.LargeShield;
import net.mortiy.gurps.rules.equipment.all.SmallShield;
import net.mortiy.gurps.rules.equipment.weapon.Weapon;
import net.mortiy.gurps.rules.equipment.weapon.all.ShortswordWeapon;
import net.mortiy.gurps.rules.map.GameMap;
import net.mortiy.gurps.rules.skills.all.Shield;
import net.mortiy.gurps.rules.skills.all.meleeweapon.sword.Shortsword;

import java.util.List;

/**
 * Combat test
 */
public class CombatTest extends TestCase {

    public void testRulebookScenario(){

        // Louis:
        Character louis = new Character(100);
        louis.setName("Louis");

        louis.getEquipment().putAndEquipItem(new ShortswordWeapon(), Body.Part.RightHand);

        louis.addSkill(new Shortsword(louis), 15);

        // Pierre:
        Character pierre = new Character(100);
        pierre.setName("Pierre");
        pierre.getEquipment().putAndEquipItem(new ShortswordWeapon(), Body.Part.RightHand);
        pierre.getEquipment().putAndEquipItem(new SmallShield(), Body.Part.LeftHand);


        pierre.addSkill(new Shield(pierre, Shield.Speciality.Shield), 12);
        pierre.addSkill(new Shortsword(pierre), 11);


        assertTrue(pierre.hasLearntSkill("Shield (Shield)"));

        GameMap gameMap = new GameMap();

        Combat combat = new Combat(louis, pierre);
        List<Fighter> fighters = combat.getFighters();

        Fighter louisFighter = fighters.get(0);
        Fighter pierreFighter = fighters.get(1);

        // Check that we have correct fighters:
        assertEquals(louis, louisFighter.getCharacter());
        assertEquals(pierre, pierreFighter.getCharacter());

        Defense.Strategy bestPierresDefenseStrategy = new Defense(pierreFighter).getBestStrategy();
        assertEquals("Ensure that <Block> is best defense strategy for Pierre", Defense.Strategy.Block, bestPierresDefenseStrategy);

        // Put fighters on the map
        gameMap.putToken(louisFighter, 5, 6);
        gameMap.putToken(pierreFighter, 7, 6);

        // Check distance between map tokens:
        assertEquals("Distance between two fighters.", 2.0, gameMap.getDistance(louisFighter, pierreFighter));

    }


    /**
     * Test for available fighter moves in yards for given Combat Maneuver
     * @throws Exception
     */
    public void testMovement() throws Exception {

        Character c1 = new Character(100);
        c1.setName("Alpha");

        Character c2 = new Character(100);
        c2.setName("Beta");

        int basicMove = (int) c1.getBasicMove().getValue();
        float basicStep = basicMove / 10f;
        int fighterStep = (basicStep < 1) ? 1 : (int) Math.floor(basicStep);

        Combat combat = new Combat(c1, c2);

        final List<Fighter> fighters = combat.getFighters();

        Fighter fighter = fighters.get(0);
        Fighter enemyFighter = fighters.get(1);


        fighter.setManeuver(new DoNothingManeuver());
        assertEquals(0, fighter.getAvailableMoves());

        fighter.setManeuver(new MoveManeuver());
        assertEquals(basicMove, fighter.getAvailableMoves());

        fighter.setManeuver(new ChangePostureManeuver(Character.Posture.Crawling));
        assertEquals(0, fighter.getAvailableMoves());

        fighter.setManeuver(new AimManeuver());
        assertEquals(fighterStep, fighter.getAvailableMoves());

        fighter.setManeuver(new EvaluateManeuver());
        assertEquals(fighterStep, fighter.getAvailableMoves());

        fighter.setManeuver(new AttackManeuver(enemyFighter, AttackManeuver.Type.Melee, fighter.getActiveWeapon()));
        assertEquals(fighterStep, fighter.getAvailableMoves());

        fighter.setManeuver(new FeintManeuver());
        assertEquals(fighterStep, fighter.getAvailableMoves());

        fighter.setManeuver(new AllOutAtackManeuver(enemyFighter, AttackManeuver.Type.Melee, fighter.getActiveWeapon()));
        assertEquals((int) Math.floor(basicMove / 2f), fighter.getAvailableMoves());

        fighter.setManeuver(new MoveAndAttackManeuver(enemyFighter, AttackManeuver.Type.Melee, fighter.getActiveWeapon()));
        assertEquals(basicMove, fighter.getAvailableMoves());

        fighter.setManeuver(new AllOutDefenseManeuver(AllOutDefenseManeuver.Type.IncreasedDefense, Defense.Strategy.Parry));
        assertEquals(fighterStep, fighter.getAvailableMoves());

        fighter.setManeuver(new AllOutDefenseManeuver(AllOutDefenseManeuver.Type.IncreasedDefense, Defense.Strategy.Dodge));
        assertEquals((int) Math.floor(basicMove / 2f), fighter.getAvailableMoves());

        fighter.setManeuver(new AllOutDefenseManeuver(AllOutDefenseManeuver.Type.DoubleDefense));
        assertEquals(fighterStep, fighter.getAvailableMoves());

        fighter.setManeuver(new ConcentrateManeuver());
        assertEquals(fighterStep, fighter.getAvailableMoves());

        fighter.setManeuver(new ReadyManeuver(fighter.getActiveWeapon()));
        assertEquals(fighterStep, fighter.getAvailableMoves());

        fighter.setManeuver(new WaitManeuver(new FeintManeuver()));
        assertEquals(fighterStep, fighter.getAvailableMoves());

        fighter.setManeuver(new WaitManeuver(new AllOutAtackManeuver(enemyFighter, AttackManeuver.Type.Melee, fighter.getActiveWeapon())));
        assertEquals((int) Math.floor(basicMove / 2f), fighter.getAvailableMoves());


    }

    public void testDefenseStrategy() throws Exception {
        Combat combat = createTestCombat();

        Fighter alphaFast = combat.getFighter(0);
        Fighter betaSmart = combat.getFighter(1);
        Fighter gammaStrong = combat.getFighter(2);

        alphaFast.setManeuver(new MoveManeuver());
        assertEquals(Defense.Strategy.Dodge, alphaFast.getDefense().getBestStrategy());

        betaSmart.setManeuver(new AllOutDefenseManeuver(AllOutDefenseManeuver.Type.DoubleDefense));
        assertEquals(Defense.Strategy.Block, betaSmart.getDefense().getBestStrategy());

        gammaStrong.setManeuver(new AttackManeuver(gammaStrong, AttackManeuver.Type.Ranged, betaSmart.getActiveWeapon()));
        assertEquals(Defense.Strategy.Block, gammaStrong.getDefense().getBestStrategy());


    }

    public void testCombat() throws Exception {

        Combat combat = createTestCombat();

        // Check that fighters ordered according to their Basic Speed
        final List<Fighter> fighters = combat.getFighters();

        Fighter f1 = fighters.get(0);
        Fighter f2 = fighters.get(1);
        Fighter f3 = fighters.get(2);

        CombatManager combatManager = new CombatManager(combat);

        FighterTurn fighterTurn;

        fighterTurn = combatManager.turn(); // f1
        assertEquals(f1, fighterTurn.getFighter());

        fighterTurn = combatManager.turn(); // f2
        assertEquals(f2, fighterTurn.getFighter());

        fighterTurn = combatManager.turn(); // f3
        assertEquals(f3, fighterTurn.getFighter());

        combatManager.startNewRound();

        combatManager.turn();

    }

    public void testAttackModifiers() throws Exception {
        Combat combat = createTestCombat();
        List<Fighter> fighters = combat.getFighters();

        Fighter alpha = fighters.get(0);
        Equipment equipment = alpha.getCharacter().getEquipment();

        equipment.putAndEquipItem(new ShortswordWeapon(), Body.Part.RightHand);
        equipment.putAndEquipItem(new LargeShield(), Body.Part.LeftHand);

        Weapon equippedWeapon = (Weapon) equipment.getEquipedItem(Body.Part.RightHand);

        assertEquals("Large shiled should give -2", -2, alpha.getAttackModifier(ManeuverType.Attack, equippedWeapon));
        assertEquals("All out attack gives + 4", 2, alpha.getAttackModifier(ManeuverType.AllOutAttack, equippedWeapon));

        assertTrue("Unequip shield", equipment.unequipItem(Body.Part.LeftHand));
        assertFalse("Try to unequipt empty hand", equipment.unequipItem(Body.Part.LeftHand));

        assertEquals(4, alpha.getAttackModifier(ManeuverType.AllOutAttack, equippedWeapon));
        assertEquals(-4, alpha.getAttackModifier(ManeuverType.MoveAndAttack, equippedWeapon));

        assertEquals("Check Short Sword required minimum character's strength", 8, equippedWeapon.getMinStrength());
        alpha.getCharacter().setBasicAttribute(Attribute.Strength, 6);

        assertEquals("Low strength should give [6 - 8 = -2]", -2, alpha.getAttackModifier(ManeuverType.Attack, equippedWeapon));

    }

    public void testCombatDamage() throws Exception {
        Combat combat = createTestCombat();
        CombatManager combatManager = new CombatManager(combat);

        Fighter f1 = combat.getFighter(0);
        Fighter f2 = combat.getFighter(1);

        f1.setManeuver(new ReadyManeuver(f1.getActiveWeapon()));
        combatManager.turn();
        assertTrue("Fighters 1 active weapon should be ready now", f1.getReadyList().contains(f1.getActiveWeapon()));
        combatManager.turn(); // Skip Fighters 2 turn;

        f1.setManeuver(new AttackManeuver(f2, AttackManeuver.Type.Melee, f1.getActiveWeapon()));
        while(f2.getCharacter().getHitpoints().getCurrentValue() > 0){
            FighterTurn fighterTurn = combatManager.turn();
            fighterTurn.getManeuverResult();
            f2.getCharacter().getHitpoints();
            if(combatManager.isRoundOver()){
                combatManager.startNewRound();
            }
        }

    }

    private Combat createTestCombat() throws Exception {

        Character c1 = new Character(150);
        c1.setBasicAttribute(Attribute.Dexterity, 16);
        c1.setName("Alpha Fast");

        Character c2 = new Character(150);
        c2.setBasicAttribute(Attribute.Intelligence, 16);
        c2.setName("Beta Smart");

        Character c3 = new Character(150);
        c3.setBasicAttribute(Attribute.Strength, 16);
        c3.setName("Gamma Strong");

        // Create equipment:
        ShortswordWeapon sword1 = new ShortswordWeapon();
        ShortswordWeapon sword2 = new ShortswordWeapon();
        ShortswordWeapon sword3 = new ShortswordWeapon();
        ShieldItem smallShield = new SmallShield();

        // Equip first character:
        c1.getEquipment().putItem(sword1);
        c1.equip(sword1, Body.Part.RightHand);

        // Equip second character:
        c2.getEquipment().putItem(sword2);
        c2.equip(sword2, Body.Part.RightHand);
        c2.equip(smallShield, Body.Part.LeftHand);
        c2.addSkill(new Shield(c3, Shield.Speciality.Shield), 16);

        //Equip third character:
        c3.getEquipment().putItem(sword3);
        c3.getEquipment().putItem(smallShield);
        c3.equip(sword3, Body.Part.RightHand);

        return new Combat(c1, c2, c3);
    }




}
