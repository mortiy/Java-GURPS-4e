package net.mortiy.gurps.rules.modifiers;

import net.mortiy.gurps.rules.Individual;
import net.mortiy.gurps.rules.table.Rolls;

/**
 * Created with IntelliJ IDEA.
 * User: oleksandr.sidko
 * Date: 12.12.12
 * Time: 19:40
 * To change this template use File | Settings | File Templates.
 */
public class ReactionModifier extends Modifier {
    public interface ReactionDeterminator {
        float determineReaction(Individual individual);
    }

    private ReactionDeterminator reactionDeterminator;

    public ReactionModifier(Individual individual, final int reactionModifier) {
        this(individual, new ReactionDeterminator() {
            @Override
            public float determineReaction(Individual individual) {
                return reactionModifier;
            }
        });
    }

    public ReactionModifier(Individual individual, ReactionDeterminator reactionDeterminator) {
        super(individual, Rolls.ReactionRoll, new SummandModifier(0));
        this.reactionDeterminator = reactionDeterminator;
    }

    public float getModifierValue(IInfluential influential) {
        return reactionDeterminator.determineReaction((Individual) influential);
    }
}
