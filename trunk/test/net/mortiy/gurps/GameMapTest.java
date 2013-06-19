package net.mortiy.gurps;

import junit.framework.TestCase;
import net.mortiy.gurps.rules.Character;
import net.mortiy.gurps.rules.map.GameMap;

/**
 * Created with IntelliJ IDEA.
 * User: oleksandr.sidko
 * Date: 13.01.13
 * Time: 21:52
 * To change this template use File | Settings | File Templates.
 */
public class GameMapTest extends TestCase {
    public void testMap() throws Exception {
        GameMap gameMap = new GameMap();

        Character tony = new Character(100);
        Character terry = new Character(100);

        gameMap.putToken(tony, 10, 10);
        gameMap.putToken(terry, 20, 10);
        assertEquals("Initial distance between two tokens", 10.0, gameMap.getDistance(terry, tony));

        gameMap.moveToken(tony, 15, 25);
        assertEquals("Check distance after moving token", 16, Math.round(gameMap.getDistance(terry, tony)));

    }
}
