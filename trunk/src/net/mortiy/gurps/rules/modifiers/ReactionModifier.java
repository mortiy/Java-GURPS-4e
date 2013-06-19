package net.mortiy.gurps.rules.modifiers;

import net.mortiy.gurps.rules.table.Rolls;
import net.mortiy.gurps.rules.Character;

/**
 * Created with IntelliJ IDEA.
 * User: oleksandr.sidko
 * Date: 12.12.12
 * Time: 19:40
 * To change this template use File | Settings | File Templates.
 */
public class ReactionModifier extends Modifier {
    public interface ReactionDeterminator {
        float determineReaction(Character character);
    }

    private ReactionDeterminator reactionDeterminator;

    public ReactionModifier(Character character, final int reactionModifier) {
        this(character, new ReactionDeterminator() {
            @Override
            public float determineReaction(Character character) {
                return reactionModifier;
            }
        });
    }

    public ReactionModifier(Character character, ReactionDeterminator reactionDeterminator) {
        super(character, Rolls.ReactionRoll, new SummandModifier(0));
        this.reactionDeterminator = reactionDeterminator;
    }

    public float getModifierValue(IInfluential influential) {
        return reactionDeterminator.determineReaction((Character) influential);
    }
}
