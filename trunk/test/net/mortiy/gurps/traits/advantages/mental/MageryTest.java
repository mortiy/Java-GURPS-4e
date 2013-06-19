package net.mortiy.gurps.traits.advantages.mental;

import junit.framework.TestCase;
import net.mortiy.gurps.rules.Character;
import net.mortiy.gurps.rules.traits.all.Magery;

/**
 * Created with IntelliJ IDEA.
 * User: oleksandr.sidko
 * Date: 13.12.12
 * Time: 23:00
 * To change this template use File | Settings | File Templates.
 */
public class MageryTest extends TestCase {
    public void testMagery() throws Exception {
        Character c = new Character(100);
        Magery magery = new Magery(c);
        c.addTrait(magery);

        assertEquals(-1, magery.getMageryLevel());
        assertEquals(0, magery.getCurrentLevel());
        assertEquals(0, magery.getCost());
        magery.increaseLevel();
        assertEquals(1, magery.getCurrentLevel());
        assertEquals(0, magery.getMageryLevel());
        assertEquals(5, magery.getCost());

        magery.increaseLevel();
        assertEquals(1, magery.getMageryLevel());
        assertEquals(15, magery.getCost());

    }
}
