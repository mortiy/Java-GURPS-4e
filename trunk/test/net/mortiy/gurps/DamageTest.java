package net.mortiy.gurps;

import junit.framework.TestCase;
import net.mortiy.gurps.rules.Character;
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
        Character character = new Character(Constants.MAX_CHARACTER_POINTS);

        RollFormula formula;
        // Strength = 10
        formula = MusclePoweredDamage.getDamageFormula(character, Type.Thrusting);
        assertEquals(1, formula.getDiceQuantity());
        assertEquals(-2, formula.getModifier());

        // Strength = 4
        character.decreaseAttribute(Attribute.Strength, 6);
        formula = MusclePoweredDamage.getDamageFormula(character, Type.Thrusting);
        assertEquals(1, formula.getDiceQuantity());
        assertEquals(-5, formula.getModifier());

        // Strength = 12
        character.increaseAttribute(Attribute.Strength, 8);
        formula = MusclePoweredDamage.getDamageFormula(character, Type.Thrusting);
        assertEquals(1, formula.getDiceQuantity());
        assertEquals(-1, formula.getModifier());

        // Strength = 14
        character.increaseAttribute(Attribute.Strength, 2);
        formula = MusclePoweredDamage.getDamageFormula(character, Type.Thrusting);
        assertEquals(1, formula.getDiceQuantity());
        assertEquals(0, formula.getModifier());

        // Strength = 16
        character.increaseAttribute(Attribute.Strength, 2);
        formula = MusclePoweredDamage.getDamageFormula(character, Type.Thrusting);
        assertEquals(1, formula.getDiceQuantity());
        assertEquals(1, formula.getModifier());

        // Strength = 19
        character.increaseAttribute(Attribute.Strength, 3);
        formula = MusclePoweredDamage.getDamageFormula(character, Type.Thrusting);
        assertEquals(2, formula.getDiceQuantity());
        assertEquals(-1, formula.getModifier());

        // Strength = 25
        character.increaseAttribute(Attribute.Strength, 6);
        formula = MusclePoweredDamage.getDamageFormula(character, Type.Thrusting);
        assertEquals(2, formula.getDiceQuantity());
        assertEquals(2, formula.getModifier());

        // Strength = 35
        character.increaseAttribute(Attribute.Strength, 10);
        formula = MusclePoweredDamage.getDamageFormula(character, Type.Thrusting);
        assertEquals(4, formula.getDiceQuantity());
        assertEquals(-1, formula.getModifier());

        // Strength = 45
        character.increaseAttribute(Attribute.Strength, 10);
        formula = MusclePoweredDamage.getDamageFormula(character, Type.Thrusting);
        assertEquals(5, formula.getDiceQuantity());
        assertEquals(0, formula.getModifier());

        // Strength = 50
        character.increaseAttribute(Attribute.Strength, 5);
        formula = MusclePoweredDamage.getDamageFormula(character, Type.Thrusting);
        assertEquals(5, formula.getDiceQuantity());
        assertEquals(2, formula.getModifier());

        // Strength = 60
        character.increaseAttribute(Attribute.Strength, 10);
        formula = MusclePoweredDamage.getDamageFormula(character, Type.Thrusting);
        assertEquals(7, formula.getDiceQuantity());
        assertEquals(-1, formula.getModifier());

        // Strength = 70
        character.increaseAttribute(Attribute.Strength, 10);
        formula = MusclePoweredDamage.getDamageFormula(character, Type.Thrusting);
        assertEquals(8, formula.getDiceQuantity());
        assertEquals(0, formula.getModifier());

        // Strength = 100
        character.increaseAttribute(Attribute.Strength, 30);
        formula = MusclePoweredDamage.getDamageFormula(character, Type.Thrusting);
        assertEquals(12, formula.getDiceQuantity());
        assertEquals(-1, formula.getModifier());

    }

    public void testSwingDamage() throws Exception {
        Character character = new Character(Constants.MAX_CHARACTER_POINTS);

        RollFormula formula;
        // Strength = 10
        formula = MusclePoweredDamage.getDamageFormula(character, Type.Swinging);
        assertEquals(1, formula.getDiceQuantity());
        assertEquals(0, formula.getModifier());

        // Strength = 7
        character.decreaseAttribute(Attribute.Strength,3);
        formula = MusclePoweredDamage.getDamageFormula(character, Type.Swinging);
        assertEquals(1, formula.getDiceQuantity());
        assertEquals(-2, formula.getModifier());

        // Strength = 12
        character.increaseAttribute(Attribute.Strength, 5);
        formula = MusclePoweredDamage.getDamageFormula(character, Type.Swinging);
        assertEquals(1, formula.getDiceQuantity());
        assertEquals(2, formula.getModifier());

        // Strength = 15
        character.increaseAttribute(Attribute.Strength, 3);
        formula = MusclePoweredDamage.getDamageFormula(character, Type.Swinging);
        assertEquals(2, formula.getDiceQuantity());
        assertEquals(1, formula.getModifier());

        // Strength = 21
        character.increaseAttribute(Attribute.Strength, 6);
        formula = MusclePoweredDamage.getDamageFormula(character, Type.Swinging);
        assertEquals(4, formula.getDiceQuantity());
        assertEquals(-1, formula.getModifier());

        // Strength = 24
        character.increaseAttribute(Attribute.Strength, 3);
        formula = MusclePoweredDamage.getDamageFormula(character, Type.Swinging);
        assertEquals(4, formula.getDiceQuantity());
        assertEquals(2, formula.getModifier());

        // Strength = 25
        character.increaseAttribute(Attribute.Strength, 1);
        formula = MusclePoweredDamage.getDamageFormula(character, Type.Swinging);
        assertEquals(5, formula.getDiceQuantity());
        assertEquals(-1, formula.getModifier());

        // Strength = 27
        character.increaseAttribute(Attribute.Strength, 2);
        formula = MusclePoweredDamage.getDamageFormula(character, Type.Swinging);
        assertEquals(5, formula.getDiceQuantity());
        assertEquals(1, formula.getModifier());

        // Strength = 30
        character.increaseAttribute(Attribute.Strength, 3);
        formula = MusclePoweredDamage.getDamageFormula(character, Type.Swinging);
        assertEquals(5, formula.getDiceQuantity());
        assertEquals(2, formula.getModifier());

        // Strength = 35
        character.increaseAttribute(Attribute.Strength, 5);
        formula = MusclePoweredDamage.getDamageFormula(character, Type.Swinging);
        assertEquals(6, formula.getDiceQuantity());
        assertEquals(1, formula.getModifier());

        // Strength = 39
        character.increaseAttribute(Attribute.Strength, 4);
        formula = MusclePoweredDamage.getDamageFormula(character, Type.Swinging);
        assertEquals(7, formula.getDiceQuantity());
        assertEquals(-1, formula.getModifier());

        // Strength = 40
        character.increaseAttribute(Attribute.Strength, 1);
        formula = MusclePoweredDamage.getDamageFormula(character, Type.Swinging);
        assertEquals(7, formula.getDiceQuantity());
        assertEquals(-1, formula.getModifier());

        // Strength = 45
        character.increaseAttribute(Attribute.Strength, 5);
        formula = MusclePoweredDamage.getDamageFormula(character, Type.Swinging);
        assertEquals(7, formula.getDiceQuantity());
        assertEquals(1, formula.getModifier());

        // Strength = 50
        character.increaseAttribute(Attribute.Strength, 5);
        formula = MusclePoweredDamage.getDamageFormula(character, Type.Swinging);
        assertEquals(8, formula.getDiceQuantity());
        assertEquals(-1, formula.getModifier());

        // Strength = 60
        character.increaseAttribute(Attribute.Strength, 10);
        formula = MusclePoweredDamage.getDamageFormula(character, Type.Swinging);
        assertEquals(9, formula.getDiceQuantity());
        assertEquals(1, formula.getModifier());

        // Strength = 80
        character.increaseAttribute(Attribute.Strength, 20);
        formula = MusclePoweredDamage.getDamageFormula(character, Type.Swinging);
        assertEquals(11, formula.getDiceQuantity());
        assertEquals(2, formula.getModifier());


    }
}
