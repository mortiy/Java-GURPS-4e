package net.mortiy.gurps;

import junit.framework.TestCase;
import net.mortiy.gurps.rules.Character;
import net.mortiy.gurps.rules.modifiers.Modifier;
import net.mortiy.gurps.rules.attributes.Attribute;
import net.mortiy.gurps.rules.modifiers.SummandModifier;
import net.mortiy.gurps.rules.table.Rolls;
import net.mortiy.gurps.rules.traits.all.Appearance;
import net.mortiy.gurps.rules.traits.all.FashionSense;
import net.mortiy.gurps.rules.traits.all.OdiousPersonalHabits;

public class ModifiersTest extends TestCase {
    public void testModifiers() throws Exception {
        Character character = new Character(100);

        character.addModifier(Attribute.Strength, new SummandModifier(-5));
        assertEquals("Character's Strength 10 with modifier -5", 5f, character.getModifiedAttribute(Attribute.Strength));

        Modifier basicSpeedModifier = character.addModifier(Attribute.BasicSpeed, new SummandModifier(-3.5f));
        assertEquals("Character's Basic Speed 5.0 with modifier -3.5f", 1.5f, character.getModifiedAttribute(Attribute.BasicSpeed));

        basicSpeedModifier.detach();
        assertEquals("Character's Basic Speed should be back to normal", 5.0f, character.getModifiedAttribute(Attribute.BasicSpeed));

    }

    public void testModifiersThruTraits() throws Exception {
        Character character = new Character(100);

        Appearance appearance = new Appearance(character);
        appearance.changeLevel(Appearance.Level.VeryBeautiful);
        assertEquals("Ensure that Very Beautiful appearance gave character +6 Reaction Roll", 6f, character.getTotalModifier(Rolls.ReactionRoll));

        FashionSense fashionSense = new FashionSense(character);
        assertEquals("Ensure that Fashion Sense gave character +1 Reaction Roll", 7f, character.getTotalModifier(Rolls.ReactionRoll));

        OdiousPersonalHabits odiousPersonalHabits = new OdiousPersonalHabits(character);
        odiousPersonalHabits.increaseLevel(3);
        assertEquals("Character get -3 Reaction Roll for Odious Personal Habbits of 3rd level", 4f, character.getTotalModifier(Rolls.ReactionRoll));

    }

    public void testModifiersDispose() throws Exception {
        Character character = new Character(100);
        assertEquals("Basic characters Fatigue Points", 10f, character.getFatiguePoints().getValue());
        Modifier fatigueModifier = character.addModifier(Attribute.FatiguePoints, new SummandModifier(5.0f));
        assertEquals("Modifier characters Fatigue Points", 15f, character.getModifiedAttribute(Attribute.FatiguePoints));
        fatigueModifier.detach();
        assertEquals("After disposing modifier, Fatigue Points are back to normal", 10f, character.getModifiedAttribute(Attribute.FatiguePoints));
    }

    /**
     * We'll increase character's Strength up to 20 for 250 milliseconds.
     * @throws InterruptedException
     */
    public void testTimeModifiers() throws InterruptedException {
        Character character = new Character(100);
        character.addTimeModifier(Attribute.Strength, 10, 250);
        assertEquals("Character become much stronger for 1/8 second", 20f, character.getModifiedAttribute(Attribute.Strength));
        Thread.sleep(100);
        assertEquals("Character is still strong", 20f, character.getModifiedAttribute(Attribute.Strength));
        Thread.sleep(200);
        assertEquals("Character should back to normal", 10f, character.getModifiedAttribute(Attribute.Strength));
    }
}
