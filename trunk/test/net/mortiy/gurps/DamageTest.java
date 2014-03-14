package net.mortiy.gurps;

import junit.framework.TestCase;
import net.mortiy.gurps.rules.Individual;
import net.mortiy.gurps.rules.attributes.Attribute;
import net.mortiy.gurps.rules.equipment.weapon.statistics.MusclePoweredDamage;
import net.mortiy.gurps.rules.equipment.weapon.statistics.MusclePoweredDamage.Type;
import net.mortiy.gurps.rules.table.RollFormula;

/**
 * Created with IntelliJ IDEA.
 * User: oleksandr.sidko
 * Date: 08.12.12
 * Time: 22:46
 * To change this template use File | Settings | File Templates.
 */
public class DamageTest extends TestCase {
    public void testThrustDamage() throws Exception {
        Individual individual = new Individual(Constants.MAX_CHARACTER_POINTS);

        RollFormula formula;
        // Strength = 10
        formula = MusclePoweredDamage.getDamageFormula(individual, Type.Thrusting);
        assertEquals(1, formula.getDiceQuantity());
        assertEquals(-2, formula.getModifier());

        // Strength = 4
        individual.decreaseAttribute(Attribute.Strength, 6);
        formula = MusclePoweredDamage.getDamageFormula(individual, Type.Thrusting);
        assertEquals(1, formula.getDiceQuantity());
        assertEquals(-5, formula.getModifier());

        // Strength = 12
        individual.increaseAttribute(Attribute.Strength, 8);
        formula = MusclePoweredDamage.getDamageFormula(individual, Type.Thrusting);
        assertEquals(1, formula.getDiceQuantity());
        assertEquals(-1, formula.getModifier());

        // Strength = 14
        individual.increaseAttribute(Attribute.Strength, 2);
        formula = MusclePoweredDamage.getDamageFormula(individual, Type.Thrusting);
        assertEquals(1, formula.getDiceQuantity());
        assertEquals(0, formula.getModifier());

        // Strength = 16
        individual.increaseAttribute(Attribute.Strength, 2);
        formula = MusclePoweredDamage.getDamageFormula(individual, Type.Thrusting);
        assertEquals(1, formula.getDiceQuantity());
        assertEquals(1, formula.getModifier());

        // Strength = 19
        individual.increaseAttribute(Attribute.Strength, 3);
        formula = MusclePoweredDamage.getDamageFormula(individual, Type.Thrusting);
        assertEquals(2, formula.getDiceQuantity());
        assertEquals(-1, formula.getModifier());

        // Strength = 25
        individual.increaseAttribute(Attribute.Strength, 6);
        formula = MusclePoweredDamage.getDamageFormula(individual, Type.Thrusting);
        assertEquals(2, formula.getDiceQuantity());
        assertEquals(2, formula.getModifier());

        // Strength = 35
        individual.increaseAttribute(Attribute.Strength, 10);
        formula = MusclePoweredDamage.getDamageFormula(individual, Type.Thrusting);
        assertEquals(4, formula.getDiceQuantity());
        assertEquals(-1, formula.getModifier());

        // Strength = 45
        individual.increaseAttribute(Attribute.Strength, 10);
        formula = MusclePoweredDamage.getDamageFormula(individual, Type.Thrusting);
        assertEquals(5, formula.getDiceQuantity());
        assertEquals(0, formula.getModifier());

        // Strength = 50
        individual.increaseAttribute(Attribute.Strength, 5);
        formula = MusclePoweredDamage.getDamageFormula(individual, Type.Thrusting);
        assertEquals(5, formula.getDiceQuantity());
        assertEquals(2, formula.getModifier());

        // Strength = 60
        individual.increaseAttribute(Attribute.Strength, 10);
        formula = MusclePoweredDamage.getDamageFormula(individual, Type.Thrusting);
        assertEquals(7, formula.getDiceQuantity());
        assertEquals(-1, formula.getModifier());

        // Strength = 70
        individual.increaseAttribute(Attribute.Strength, 10);
        formula = MusclePoweredDamage.getDamageFormula(individual, Type.Thrusting);
        assertEquals(8, formula.getDiceQuantity());
        assertEquals(0, formula.getModifier());

        // Strength = 100
        individual.increaseAttribute(Attribute.Strength, 30);
        formula = MusclePoweredDamage.getDamageFormula(individual, Type.Thrusting);
        assertEquals(12, formula.getDiceQuantity());
        assertEquals(-1, formula.getModifier());

    }

    public void testSwingDamage() throws Exception {
        Individual individual = new Individual(Constants.MAX_CHARACTER_POINTS);

        RollFormula formula;
        // Strength = 10
        formula = MusclePoweredDamage.getDamageFormula(individual, Type.Swinging);
        assertEquals(1, formula.getDiceQuantity());
        assertEquals(0, formula.getModifier());

        // Strength = 7
        individual.decreaseAttribute(Attribute.Strength,3);
        formula = MusclePoweredDamage.getDamageFormula(individual, Type.Swinging);
        assertEquals(1, formula.getDiceQuantity());
        assertEquals(-2, formula.getModifier());

        // Strength = 12
        individual.increaseAttribute(Attribute.Strength, 5);
        formula = MusclePoweredDamage.getDamageFormula(individual, Type.Swinging);
        assertEquals(1, formula.getDiceQuantity());
        assertEquals(2, formula.getModifier());

        // Strength = 15
        individual.increaseAttribute(Attribute.Strength, 3);
        formula = MusclePoweredDamage.getDamageFormula(individual, Type.Swinging);
        assertEquals(2, formula.getDiceQuantity());
        assertEquals(1, formula.getModifier());

        // Strength = 21
        individual.increaseAttribute(Attribute.Strength, 6);
        formula = MusclePoweredDamage.getDamageFormula(individual, Type.Swinging);
        assertEquals(4, formula.getDiceQuantity());
        assertEquals(-1, formula.getModifier());

        // Strength = 24
        individual.increaseAttribute(Attribute.Strength, 3);
        formula = MusclePoweredDamage.getDamageFormula(individual, Type.Swinging);
        assertEquals(4, formula.getDiceQuantity());
        assertEquals(2, formula.getModifier());

        // Strength = 25
        individual.increaseAttribute(Attribute.Strength, 1);
        formula = MusclePoweredDamage.getDamageFormula(individual, Type.Swinging);
        assertEquals(5, formula.getDiceQuantity());
        assertEquals(-1, formula.getModifier());

        // Strength = 27
        individual.increaseAttribute(Attribute.Strength, 2);
        formula = MusclePoweredDamage.getDamageFormula(individual, Type.Swinging);
        assertEquals(5, formula.getDiceQuantity());
        assertEquals(1, formula.getModifier());

        // Strength = 30
        individual.increaseAttribute(Attribute.Strength, 3);
        formula = MusclePoweredDamage.getDamageFormula(individual, Type.Swinging);
        assertEquals(5, formula.getDiceQuantity());
        assertEquals(2, formula.getModifier());

        // Strength = 35
        individual.increaseAttribute(Attribute.Strength, 5);
        formula = MusclePoweredDamage.getDamageFormula(individual, Type.Swinging);
        assertEquals(6, formula.getDiceQuantity());
        assertEquals(1, formula.getModifier());

        // Strength = 39
        individual.increaseAttribute(Attribute.Strength, 4);
        formula = MusclePoweredDamage.getDamageFormula(individual, Type.Swinging);
        assertEquals(7, formula.getDiceQuantity());
        assertEquals(-1, formula.getModifier());

        // Strength = 40
        individual.increaseAttribute(Attribute.Strength, 1);
        formula = MusclePoweredDamage.getDamageFormula(individual, Type.Swinging);
        assertEquals(7, formula.getDiceQuantity());
        assertEquals(-1, formula.getModifier());

        // Strength = 45
        individual.increaseAttribute(Attribute.Strength, 5);
        formula = MusclePoweredDamage.getDamageFormula(individual, Type.Swinging);
        assertEquals(7, formula.getDiceQuantity());
        assertEquals(1, formula.getModifier());

        // Strength = 50
        individual.increaseAttribute(Attribute.Strength, 5);
        formula = MusclePoweredDamage.getDamageFormula(individual, Type.Swinging);
        assertEquals(8, formula.getDiceQuantity());
        assertEquals(-1, formula.getModifier());

        // Strength = 60
        individual.increaseAttribute(Attribute.Strength, 10);
        formula = MusclePoweredDamage.getDamageFormula(individual, Type.Swinging);
        assertEquals(9, formula.getDiceQuantity());
        assertEquals(1, formula.getModifier());

        // Strength = 80
        individual.increaseAttribute(Attribute.Strength, 20);
        formula = MusclePoweredDamage.getDamageFormula(individual, Type.Swinging);
        assertEquals(11, formula.getDiceQuantity());
        assertEquals(2, formula.getModifier());


    }
}
