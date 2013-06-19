package net.mortiy.gurps.rules.traits.all;

import net.mortiy.gurps.rules.modifiers.Modifier;
import net.mortiy.gurps.rules.modifiers.SummandModifier;
import net.mortiy.gurps.rules.table.Rolls;
import net.mortiy.gurps.rules.traits.Advantage;
import net.mortiy.gurps.rules.traits.Disadvantage;
import net.mortiy.gurps.rules.traits.VariableTrait;
import net.mortiy.gurps.rules.Character;
import net.mortiy.gurps.rules.traits.categories.Physical;


/**
 * Appearance is mostly a “special effect” – you may choose any physical
 * appearance you like. At minimum, note the color of your skin, hair, and
 * eyes (or other features appropriate to your race: scales, feathers, paint job,
 * etc.). However, certain traits count as advantages or disadvantages
 */
public class Appearance extends VariableTrait implements Advantage, Disadvantage, Physical {

    public static enum Level implements ITraitLevel {
                        // Reaction Roll:
        Horrific,       // -6
        Monstrous,      // -5
        Hideous,        // -4
        Ugly,           // -2
        Unattractive,   // -1
        Average,        //  0
        Attractive,     // +1
        Beautiful,      // +4
        VeryBeautiful,  // +6
        Transcendent,    // +8

        // Specials:
        Androgynous,
        Impressive,
        Universal,
        OffTheShelfLook

    }

    Modifier reactionModifier;
    private int reactionModifiers[] = new int[] {-6, -5, -4, -2, -1, 0, 1, 4, 6, 8, 0, 0, 0, 0};

    public Appearance(Character character) {
        super(character, "Appearance");
        //
        levelsCost = new int[]{-24, -20, -16, -8, -4, 0, 4, 12, 16, 20};
        reactionModifier = addModifier(Rolls.ReactionRoll, new SummandModifier(0));
        changeLevel(Level.Average);
    }

    private int getReactionModifier(){
        return reactionModifiers[currentLevel.ordinal()];
    }

    @Override
    public void onLevelChange(ITraitLevel level) {
        reactionModifier.setModifierValue(new SummandModifier(getReactionModifier()));
    }


}
