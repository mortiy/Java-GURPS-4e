package net.mortiy.gurps;

import junit.framework.TestCase;
import net.mortiy.gurps.rules.Character;
import net.mortiy.gurps.rules.attributes.Attribute;
import net.mortiy.gurps.rules.attributes.basic.Intelligence;
import net.mortiy.gurps.rules.skills.Skill;
import net.mortiy.gurps.rules.skills.SkillDefault;
import net.mortiy.gurps.rules.skills.all.Accounting;
import net.mortiy.gurps.rules.skills.all.Bicycling;
import net.mortiy.gurps.rules.skills.all.Mathematics;
import net.mortiy.gurps.rules.skills.all.meleeweapon.sword.TwoHandedSword;
import net.mortiy.gurps.rules.skills.all.meleeweapon.whip.Kusari;
import net.mortiy.gurps.rules.table.DiceRoller;
import net.mortiy.gurps.rules.table.rolls.SuccessRoll;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: oleksandr.sidko
 * Date: 08.12.12
 * Time: 10:46
 * To change this template use File | Settings | File Templates.
 */
public class SkillTest extends TestCase {

    public void testSkillLearning() throws Exception {

        Character character = new Character(Constants.MAX_CHARACTER_POINTS);

        character.increaseAttribute(Attribute.Intelligence, 5); // 15
        character.increaseAttribute(Attribute.Dexterity, 2); // 12

        Skill accounting = new Accounting(character); // IQ/Hard
        assertFalse(character.hasLearntSkill("Accounting"));
        Skill bicycling = new Bicycling(character); // DX/Easy
        bicycling.setLevel(5);
        assertTrue(character.hasLearntSkill("Bicycling"));
        Skill musicalInstrument = new Skill(character, "Musical Instrument", "Piano", Attribute.Intelligence, Skill.Difficulty.Hard);
        musicalInstrument.setLevel(5);
        assertTrue("Check if character can play piano.", character.hasLearntSkill("Musical Instrument (Piano)"));

        accounting.setLevel(10);
        bicycling.setLevel(10);
        musicalInstrument.setLevel(10);

        int intelligenceLevel = character.getBasicAttribute(Attribute.Intelligence).getLevel();
        int dexterityLevel = character.getBasicAttribute(Attribute.Dexterity).getLevel();

        // IQ/Hard
        assertEquals("Cost of Hard skill 'Attribute-2'", 1, accounting.getCost(intelligenceLevel - 2));
        assertEquals("Cost of Hard skill 'Attribute-1'", 2, accounting.getCost(intelligenceLevel - 1));
        assertEquals("Cost of Hard skill 'Attribute+1'", 8, accounting.getCost(intelligenceLevel + 1));
        assertEquals("Cost of Hard skill 'Attribute+5'", 24, accounting.getCost(intelligenceLevel + 5));
        assertEquals("Cost of Hard skill 'Attribute+10 by formula", 44, accounting.getCost(intelligenceLevel + 10));

        // DX/Easy
        assertEquals("Cost of Easy skill 'Attribute+0'", 1, bicycling.getCost(dexterityLevel));
        assertEquals("Cost of Easy skill 'Attribute+5'", 16, bicycling.getCost(dexterityLevel + 5));
        assertEquals("Cost of Easy skill 'Attribute+9'", 36, bicycling.getCost(dexterityLevel + 10));


    }

    public void testLearningCost() throws Exception {
        Character character = new Character(100);
        new Accounting(character);

    }

    public void testSkillDefaults() throws Exception, SkillDefault.DefaultSkillNotFoundException {

        int FINANCE_SKILL_LEVEL = 12;

        Character character = new Character(Constants.MAX_CHARACTER_POINTS);
        Intelligence intelligence = (Intelligence) character.getAttribute(Attribute.Intelligence);
        character.increaseAttribute(Attribute.Intelligence, 2);
        assertEquals("Check Intelligence level", 12, intelligence.getLevel());

        Skill financeSkill = new Skill(character, "Finance", Attribute.Intelligence, Skill.Difficulty.Hard);
        financeSkill.setLevel(FINANCE_SKILL_LEVEL);


        // Accounting defaults (IQ-6, Finance-4)
        Skill accountingSkill = new Accounting(character);
        SkillDefault bestSkillDefault;
        int bestDefaultLevel;

        // Character IQ = 12
        // Finance Skill Level = 12
        bestSkillDefault = accountingSkill.findBestSkillDefault();

        List<SkillDefault> skillDefaults = accountingSkill.getSkillDefaults();

        SkillDefault defaultByIQ = skillDefaults.get(0);
        SkillDefault defaultByFinances = skillDefaults.get(1);
        SkillDefault defaultByMathematics = skillDefaults.get(2);
        SkillDefault defaultByMerchant = skillDefaults.get(3);

        assertEquals("Check default by IQ-6", 6, accountingSkill.getDefaultSkillLevel(defaultByIQ));
        assertEquals("Check default by Finances-4", 8, accountingSkill.getDefaultSkillLevel(defaultByFinances));
        assertEquals("Check default by Mathematics-5", -5, accountingSkill.getDefaultSkillLevel(defaultByMathematics));
        assertEquals("Check default by Merchant-5", -5, accountingSkill.getDefaultSkillLevel(defaultByMerchant));

        assertEquals("Check that current best default is Finance Skill", financeSkill.getKey(), ((Skill) bestSkillDefault.skillDefault).getKey());
        bestDefaultLevel = accountingSkill.getDefaultSkillLevel(bestSkillDefault);
        assertEquals("Finance level comparison", FINANCE_SKILL_LEVEL - 4, bestDefaultLevel);
        assertEquals("Check that Accounting default level equals to best default level", accountingSkill.getSkillLevel(), bestDefaultLevel);

        // Character IQ = 15
        // Finance Skill Level = 12
        character.increaseAttribute(Attribute.Intelligence, 3);
        int intelligenceLevel = character.getBasicAttribute(Attribute.Intelligence).getLevel();
        bestSkillDefault = accountingSkill.findBestSkillDefault();
        assertEquals("Check that current best default is Intelligence Attribute", Attribute.Intelligence, ((Attribute) bestSkillDefault.skillDefault));

        Mathematics mathematics = new Mathematics(character);

        bestDefaultLevel = accountingSkill.getDefaultSkillLevel(bestSkillDefault);
        assertEquals("Intelligence level comparison", intelligenceLevel - 6, bestDefaultLevel);
    }

    public void testSkillUsage() {
        Character character = new Character(Constants.MAX_CHARACTER_POINTS);
        Accounting accountingSkill = new Accounting(character);
        character.addSkill(accountingSkill);
        accountingSkill.setLevel(40);
        SuccessRoll successRoll = null;
        try {
            successRoll = character.useSkill("Accounting");
        } catch (Character.SkillNotFoundException e) {
            e.printStackTrace();
        }
        assertEquals(DiceRoller.RollResult.CriticalSuccess, successRoll.getRollResult());


    }

    public void testSkillInstantiation(){
        Character character = new Character(Constants.MAX_CHARACTER_POINTS);
        character.addSkill(new Kusari(character), 15);
        character.addSkill(new TwoHandedSword(character), 15);
        character.getSkill("Two-Handed Sword");
        character.getSkill("Jitte/Sai");
    }
}
