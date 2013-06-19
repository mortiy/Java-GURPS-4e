package net.mortiy.gurps;

import junit.framework.TestCase;
import net.mortiy.gurps.Constants;
import net.mortiy.gurps.rules.Character;
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

        net.mortiy.gurps.rules.Character character = new Character(Constants.MAX_CHARACTER_POINTS);

        assertEquals(10, character.getBasicAttribute(Attribute.Dexterity).getLevel());

        character.increaseAttribute(Attribute.Dexterity); // 0 + 20 = 20
        assertEquals(11, character.getBasicAttribute(Attribute.Dexterity).getLevel());
        assertEquals(20f, character.getCharacterCost());

        character.increaseAttribute(Attribute.Intelligence, 3); // 20 + 60 = 80
        assertEquals(13, character.getBasicAttribute(Attribute.Intelligence).getLevel());
        assertEquals(80f, character.getCharacterCost());

        character.decreaseAttribute(Attribute.Health, 5); // 80 - 50 = 30
        assertEquals(5, character.getBasicAttribute(Attribute.Health).getLevel());
        assertEquals(30f, character.getCharacterCost());

        character.decreaseAttribute(Attribute.Strength, 2); // 30 - 20 = 10
        assertEquals(8, character.getBasicAttribute(Attribute.Strength).getLevel());
        assertEquals(10f, character.getCharacterCost());

        assertEquals(Constants.MAX_CHARACTER_POINTS - character.getCharacterCost(), character.getRemainingPoints());

        assertEquals(11, character.getBasicAttribute(Attribute.Dexterity).getLevel());
        assertEquals(13, character.getBasicAttribute(Attribute.Intelligence).getLevel());
        assertEquals(5, character.getBasicAttribute(Attribute.Health).getLevel());
        assertEquals(8, character.getBasicAttribute(Attribute.Strength).getLevel());

    }
}
