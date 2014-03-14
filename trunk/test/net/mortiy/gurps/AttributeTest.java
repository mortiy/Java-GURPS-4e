package net.mortiy.gurps;

import junit.framework.TestCase;
import net.mortiy.gurps.rules.Individual;
import net.mortiy.gurps.rules.attributes.Attribute;

/**
 * Created with IntelliJ IDEA.
 * User: oleksandr.sidko
 * Date: 08.12.12
 * Time: 11:41
 * To change this template use File | Settings | File Templates.
 */
public class AttributeTest extends TestCase {

    public void testAttributes() throws Exception {

        Individual individual = new Individual(Constants.MAX_CHARACTER_POINTS);

        assertEquals(10, individual.getBasicAttribute(Attribute.Dexterity).getLevel());

        individual.increaseAttribute(Attribute.Dexterity); // 0 + 20 = 20
        assertEquals(11, individual.getBasicAttribute(Attribute.Dexterity).getLevel());
        assertEquals(20f, individual.getCharacterCost());

        individual.increaseAttribute(Attribute.Intelligence, 3); // 20 + 60 = 80
        assertEquals(13, individual.getBasicAttribute(Attribute.Intelligence).getLevel());
        assertEquals(80f, individual.getCharacterCost());

        individual.decreaseAttribute(Attribute.Health, 5); // 80 - 50 = 30
        assertEquals(5, individual.getBasicAttribute(Attribute.Health).getLevel());
        assertEquals(30f, individual.getCharacterCost());

        individual.decreaseAttribute(Attribute.Strength, 2); // 30 - 20 = 10
        assertEquals(8, individual.getBasicAttribute(Attribute.Strength).getLevel());
        assertEquals(10f, individual.getCharacterCost());

        assertEquals(Constants.MAX_CHARACTER_POINTS - individual.getCharacterCost(), individual.getRemainingPoints());

        assertEquals(11, individual.getBasicAttribute(Attribute.Dexterity).getLevel());
        assertEquals(13, individual.getBasicAttribute(Attribute.Intelligence).getLevel());
        assertEquals(5, individual.getBasicAttribute(Attribute.Health).getLevel());
        assertEquals(8, individual.getBasicAttribute(Attribute.Strength).getLevel());

    }
}
