package net.mortiy.gurps;

import junit.framework.TestCase;
import net.mortiy.gurps.rules.*;
import net.mortiy.gurps.rules.Individual;
import net.mortiy.gurps.rules.traits.all.Wealth;


public class WorldTest extends TestCase {
    public void testAddingCharacter() throws Exception {
        // Create Digital Age (TL8) world
        // Starting wealth - $20`000
        World world = new World(TechLevel.TL8);

        Individual c = new Individual(100);
        c.addTrait(new Wealth(c)).changeLevel(Wealth.Levels.Poor);
        world.addCharacter(c);
        assertEquals("Individual with Poor Wealth at TL 8", 4000, c.getEquipment().getItem("Money").getQuantity());
    }
}
