package net.mortiy.gurps.rules.traits.all;

import net.mortiy.gurps.rules.modifiers.ReactionModifier;
import net.mortiy.gurps.rules.traits.Disadvantage;

import net.mortiy.gurps.rules.Character;
import net.mortiy.gurps.rules.traits.SelfControlDisadvantage;

/**
 * Overconfidence
 * ======================
 * You believe that you are far more powerful, intelligent, or competent than you really are.
 * You may be proud and boastful or just quietly determined, but you must roleplay this trait.
 * You must make a self-control roll any time the GM feels you show an
 * unreasonable degree of caution.
 * If you fail, you must go ahead as though you were able to handle the situation!
 * Caution is not an option.
 * You receive +2 on all reaction rolls from young or naive individuals (who
 * believe you are as good as you say you are), but -2 on reactions from
 * experienced NPCs.
 */
public class Overconfidence extends SelfControlDisadvantage implements Disadvantage {
    public Overconfidence(Character character, Frequency frequency) {
        super(character, "Overconfidence", -5, frequency);

        addModifier(
                new ReactionModifier(character, new ReactionModifier.ReactionDeterminator() {
                    @Override
                    public float determineReaction(Character character) {
                        if (character.getAge() > 30) {
                            return -2;
                        } else {
                            return +2;
                        }
                    }
                })
        );

    }


}
