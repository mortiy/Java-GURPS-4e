package net.mortiy.gurps.rules.traits.all;

import net.mortiy.gurps.rules.Individual;
import net.mortiy.gurps.rules.modifiers.Modifier;
import net.mortiy.gurps.rules.modifiers.SummandModifier;
import net.mortiy.gurps.rules.table.Rolls;
import net.mortiy.gurps.rules.traits.Advantage;
import net.mortiy.gurps.rules.traits.Disadvantage;
import net.mortiy.gurps.rules.traits.VariableTrait;
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
        Horrific(-24),       // -6
        Monstrous(-20),      // -5
        Hideous(-16),        // -4
        Ugly(-8),           // -2
        Unattractive(-4),   // -1
        Average(0),        //  0
        Attractive(4),     // +1
        Beautiful(12),      // +4
        VeryBeautiful(16),  // +6
        Transcendent(20),    // +8

        // Specials:
        Androgynous(0),
        Impressive(0),
        Universal(0),
        OffTheShelfLook(0);

        private final int cost;

        Level(int cost) {
            this.cost = cost;
        }

        @Override
        public int getCost() {
            return cost;
        }

    }

    Modifier reactionModifier;
    private int reactionModifiers[] = new int[] {-6, -5, -4, -2, -1, 0, 1, 4, 6, 8, 0, 0, 0, 0};

    public Appearance(Individual individual) {
        super(individual, "Appearance");
        //
        reactionModifier = registerModifier(Rolls.ReactionRoll, new SummandModifier(0));
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
