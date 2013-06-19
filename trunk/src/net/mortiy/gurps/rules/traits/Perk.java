package net.mortiy.gurps.rules.traits;

import net.mortiy.gurps.rules.Character;

/**
 * A “perk” is a very minor advantage, worth only 1 point.
 * Perks cannot be modified with enhancements or limitations,
 * and they can be added in play without upsetting game balance.
 * Otherwise, perks use the same rules as other advantages.
 *
 * The GM is encouraged to create new perks.
 * No perk should provide wealth, social standing, or combat bonuses.
 * A perk can provide a modest bonus (up to +2) to an attribute, skill,
 * or reaction roll in relatively rare circumstances.
 * The GM may allow more generous bonuses, if they apply only in extremely rare situations.
 */
public class Perk extends Trait implements Advantage {

    public Perk(Character character, String name) {
        super(character, name, CostType.Fixed, 1);
    }

    @Override
    public void addModifier(TraitModifier modifier) {
        return;
    }

    @Override
    public void addLimitation(TraitLimitation limitation) {
        return;
    }
}
