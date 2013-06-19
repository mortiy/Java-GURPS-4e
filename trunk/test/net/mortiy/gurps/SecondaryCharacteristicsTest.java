package net.mortiy.gurps;

import junit.framework.TestCase;
import net.mortiy.gurps.rules.Character;
import net.mortiy.gurps.rules.attributes.Attribute;
import net.mortiy.gurps.rules.attributes.CharacterAttribute;
import net.mortiy.gurps.rules.attributes.secondary.BasicMove;
import net.mortiy.gurps.rules.attributes.secondary.BasicSpeed;
import net.mortiy.gurps.rules.attributes.secondary.Dodge;

public class SecondaryCharacteristicsTest extends TestCase {
    Character character = new Character(Constants.MAX_CHARACTER_POINTS);

    public void testBasicSpeedAndMove() throws Exception {

        BasicSpeed basicSpeed = character.getBasicSpeed();
        BasicMove basicMove = character.getBasicMove();
        Dodge dodge = character.getDodge();

        assertEquals("Basic Speed at DX 10 and HT 10", 5.0f, basicSpeed.getValue());
        character.increaseAttribute(Attribute.Dexterity, 2);
        character.increaseAttribute(Attribute.Health, 4);
        assertEquals("Basic Speed at DX 12 and HT 14", 6.5f, basicSpeed.getValue());
        assertEquals("Dodge at Basic Speed 6.5", 9.0f, dodge.getValue());

        character.increaseAttribute(Attribute.BasicSpeed, 1.5f);
        assertEquals("Basic Speed at DX 12 and HT 14 + 1.5 trained", 8.0f, basicSpeed.getValue());
        assertEquals("Cost of +1.5 Basic Speed points", 30f, basicSpeed.getCost());

        character.increaseAttribute(Attribute.BasicSpeed, 0.25f);
        assertEquals("Basic Move at Basic Speed 8.25", 8.0f, basicMove.getValue());

        assertEquals("Dodge at Basic Speed 8.25", 11.0f, dodge.getValue());

    }

    public void testBasicLift() throws Exception {

        CharacterAttribute basicLift = character.getBasicLift();

        assertEquals("Basic Lift at ST 10", 20f, basicLift.getValue());
        character.increaseAttribute(Attribute.Strength, 5);
        assertEquals("Basic Lift at ST 15", 45f, basicLift.getValue());

    }

    public void testHitPoints() throws  Exception {
        CharacterAttribute hitPoints = character.getHitpoints();
        assertEquals("Hit Points at ST 10", 10f, hitPoints.getValue());

        character.increaseAttribute(Attribute.Strength, 5);
        assertEquals("Hit Points at ST 15", 15f, hitPoints.getValue());

        character.increaseAttribute(Attribute.HitPoints, 5);
        assertEquals("Hit points at ST 15 + 5 bought", 20f, hitPoints.getValue());
        assertEquals("Cost of +5 Hit Points", 10f, hitPoints.getCost());

        character.getBody().setSizeModifier(4);
        assertEquals("Cost with Size Modifier +4", 6f, hitPoints.getCost());

    }

    public void testWillAndPerception() throws Exception {
        CharacterAttribute will = character.getWill();
        CharacterAttribute perception = character.getPerception();

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
