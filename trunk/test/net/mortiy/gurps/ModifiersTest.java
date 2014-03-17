package net.mortiy.gurps;

import junit.framework.TestCase;
import net.mortiy.gurps.rules.Individual;
import net.mortiy.gurps.rules.modifiers.Modifier;
import net.mortiy.gurps.rules.attributes.Attribute;
import net.mortiy.gurps.rules.modifiers.SummandModifier;
import net.mortiy.gurps.rules.table.Rolls;
import net.mortiy.gurps.rules.traits.all.Appearance;
import net.mortiy.gurps.rules.traits.all.FashionSense;
import net.mortiy.gurps.rules.traits.all.OdiousPersonalHabits;

public class ModifiersTest extends TestCase {
    public void testModifiers() throws Exception {
        Individual individual = new Individual(100);

        individual.addModifier(Attribute.Strength, new SummandModifier(-5));
        assertEquals("Individual's Strength 10 with modifier -5", 5f, individual.getModifiedAttribute(Attribute.Strength));

        Modifier basicSpeedModifier = individual.addModifier(Attribute.BasicSpeed, new SummandModifier(-3.5f));
        assertEquals("Individual's Basic Speed 5.0 with modifier -3.5f", 1.5f, individual.getModifiedAttribute(Attribute.BasicSpeed));

        basicSpeedModifier.detach();
        assertEquals("Individual's Basic Speed should be back to normal", 5.0f, individual.getModifiedAttribute(Attribute.BasicSpeed));

    }

    public void testModifiersThruTraits() throws Exception {
        Individual individual = new Individual(100);

        Appearance appearance = new Appearance(individual);
        appearance.changeLevel(Appearance.Level.VeryBeautiful);
        assertEquals("Ensure that Very Beautiful appearance gave individual +6 Reaction Roll", 6, (int) individual.getTotalModifier(Rolls.ReactionRoll));

        FashionSense fashionSense = new FashionSense(individual);
        assertEquals("Ensure that Fashion Sense gave individual +1 Reaction Roll", 7, (int) individual.getTotalModifier(Rolls.ReactionRoll));

        OdiousPersonalHabits odiousPersonalHabits = new OdiousPersonalHabits(individual);
        odiousPersonalHabits.increaseLevel(3);
        assertEquals("Individual get -3 Reaction Roll for Odious Personal Habbits of 3rd level", 4, (int) individual.getTotalModifier(Rolls.ReactionRoll));

    }

    public void testModifiersDispose() throws Exception {
        Individual individual = new Individual(100);
        assertEquals("Basic characters Fatigue Points", 10f, individual.getFatiguePoints().getValue());
        Modifier fatigueModifier = individual.addModifier(Attribute.FatiguePoints, new SummandModifier(5.0f));
        assertEquals("Modifier characters Fatigue Points", 15f, individual.getModifiedAttribute(Attribute.FatiguePoints));
        fatigueModifier.detach();
        assertEquals("After disposing modifier, Fatigue Points are back to normal", 10f, individual.getModifiedAttribute(Attribute.FatiguePoints));
    }

    /**
     * We'll increase character's Strength up to 20 for 250 milliseconds.
     * @throws InterruptedException
     */
    public void testTimeModifiers() throws InterruptedException {
        Individual individual = new Individual(100);
        individual.addTimeModifier(Attribute.Strength, 10, 250);
        assertEquals("Individual become much stronger for 1/8 second", 20f, individual.getModifiedAttribute(Attribute.Strength));
        Thread.sleep(100);
        assertEquals("Individual is still strong", 20f, individual.getModifiedAttribute(Attribute.Strength));
        Thread.sleep(200);
        assertEquals("Individual should back to normal", 10f, individual.getModifiedAttribute(Attribute.Strength));
    }
}
