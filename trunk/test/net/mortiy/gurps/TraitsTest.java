package net.mortiy.gurps;

import junit.framework.TestCase;
import net.mortiy.gurps.rules.Character;
import net.mortiy.gurps.rules.ModifiersList;
import net.mortiy.gurps.rules.table.Rolls;
import net.mortiy.gurps.rules.traits.all.Appearance;
import net.mortiy.gurps.rules.traits.all.OdiousPersonalHabits;

/**
 * Created with IntelliJ IDEA.
 * User: oleksandr.sidko
 * Date: 10.12.12
 * Time: 0:43
 * To change this template use File | Settings | File Templates.
 */
public class TraitsTest extends TestCase {


    public void testVariableTraits() throws Exception, Character.TraitNotFoundException, Character.CharacterAlreadyHasTraitException {

        Character character = new Character(Constants.MAX_CHARACTER_POINTS);

        Appearance appearance = new Appearance(character);
        character.addTrait(appearance);
            appearance.changeLevel(Appearance.Level.VeryBeautiful);
            assertTrue("Getting trait by name", appearance instanceof Appearance);
            assertEquals("Check character cost with Very Beautiful Appearance", 16, appearance.getRawCost());
            assertTrue(character.hasTrait("Appearance"));
            assertEquals("Check current appearance level",
                    Appearance.Level.VeryBeautiful,
                    ((Appearance) character.getTrait("Appearance")).getLevel()
            );

    }

    public void testLevelTraits() throws Exception, Character.TraitNotFoundException, Character.CharacterAlreadyHasTraitException {

        Character character = new Character(Constants.MAX_CHARACTER_POINTS);

        OdiousPersonalHabits odiousPersonalHabits = new OdiousPersonalHabits(character);
        character.addTrait(odiousPersonalHabits);
        assertEquals("Character doesn't have OPH yet", 0, odiousPersonalHabits.getCurrentLevel());

        odiousPersonalHabits.increaseLevel();
        assertEquals("Character has OPH of the 1st level", 1, odiousPersonalHabits.getCurrentLevel());
        assertEquals("OPH of 1st level costs character -5 points", -5, odiousPersonalHabits.getRawCost());

        ModifiersList reactionModifier = character.getModifiersList(Rolls.ReactionRoll);
        assertEquals("OPH of 1st level gives character -1 to Reaction Roll", -1f, reactionModifier.getTotalSummand());

        odiousPersonalHabits.increaseLevel(2);
        assertEquals("Character has OPH of the 3rd level", 3, odiousPersonalHabits.getCurrentLevel());
        assertEquals("OPH of 3rd level gives character -3 to Reaction Roll", -3f, reactionModifier.getTotalSummand());

        odiousPersonalHabits.increaseLevel();
        assertEquals("Maximum possible OPH level is 3, so no change", 3, odiousPersonalHabits.getCurrentLevel());

    }
}
