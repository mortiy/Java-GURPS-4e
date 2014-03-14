package net.mortiy.gurps;

import junit.framework.TestCase;
import net.mortiy.gurps.rules.Individual;
import net.mortiy.gurps.rules.attributes.Attribute;
import net.mortiy.gurps.rules.attributes.CharacterAttribute;
import net.mortiy.gurps.rules.attributes.secondary.BasicMove;
import net.mortiy.gurps.rules.attributes.secondary.BasicSpeed;
import net.mortiy.gurps.rules.attributes.secondary.Dodge;

public class SecondaryCharacteristicsTest extends TestCase {
    Individual individual = new Individual(Constants.MAX_CHARACTER_POINTS);

    public void testBasicSpeedAndMove() throws Exception {

        BasicSpeed basicSpeed = individual.getBasicSpeed();
        BasicMove basicMove = individual.getBasicMove();
        Dodge dodge = individual.getDodge();

        assertEquals("Basic Speed at DX 10 and HT 10", 5.0f, basicSpeed.getValue());
        individual.increaseAttribute(Attribute.Dexterity, 2);
        individual.increaseAttribute(Attribute.Health, 4);
        assertEquals("Basic Speed at DX 12 and HT 14", 6.5f, basicSpeed.getValue());
        assertEquals("Dodge at Basic Speed 6.5", 9.0f, dodge.getValue());

        individual.increaseAttribute(Attribute.BasicSpeed, 1.5f);
        assertEquals("Basic Speed at DX 12 and HT 14 + 1.5 trained", 8.0f, basicSpeed.getValue());
        assertEquals("Cost of +1.5 Basic Speed points", 30f, basicSpeed.getCost());

        individual.increaseAttribute(Attribute.BasicSpeed, 0.25f);
        assertEquals("Basic Move at Basic Speed 8.25", 8.0f, basicMove.getValue());

        assertEquals("Dodge at Basic Speed 8.25", 11.0f, dodge.getValue());

    }

    public void testBasicLift() throws Exception {

        CharacterAttribute basicLift = individual.getBasicLift();

        assertEquals("Basic Lift at ST 10", 20f, basicLift.getValue());
        individual.increaseAttribute(Attribute.Strength, 5);
        assertEquals("Basic Lift at ST 15", 45f, basicLift.getValue());

    }

    public void testHitPoints() throws  Exception {
        CharacterAttribute hitPoints = individual.getHitpoints();
        assertEquals("Hit Points at ST 10", 10f, hitPoints.getValue());

        individual.increaseAttribute(Attribute.Strength, 5);
        assertEquals("Hit Points at ST 15", 15f, hitPoints.getValue());

        individual.increaseAttribute(Attribute.HitPoints, 5);
        assertEquals("Hit points at ST 15 + 5 bought", 20f, hitPoints.getValue());
        assertEquals("Cost of +5 Hit Points", 10f, hitPoints.getCost());

        individual.getBody().setSizeModifier(4);
        assertEquals("Cost with Size Modifier +4", 6f, hitPoints.getCost());

    }

    public void testWillAndPerception() throws Exception {
        CharacterAttribute will = individual.getWill();
        CharacterAttribute perception = individual.getPerception();

        assertEquals("Will at IQ 10", 10f, will.getValue());
        assertEquals("Perception at IQ 10", 10f, perception.getValue());

        will.increase(5);
        assertEquals("Will at IQ 10 + 5 points trained", 15f, will.getValue());
        assertEquals("Will at IQ 10 + 5 points cost", 25f, will.getCost());

        perception.increase(8);
        assertEquals("Perception at IQ 10 + 8 points trained", 18f, perception.getValue());
        assertEquals("Perception at IQ 10 + 8 points cost", 40f, perception.getCost());

    }
}
