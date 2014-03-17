package net.mortiy.gurps.rules.hazards.afflictions;

import net.mortiy.gurps.rules.Individual;
import net.mortiy.gurps.rules.attributes.Attribute;
import net.mortiy.gurps.rules.hazards.Affliction;
import net.mortiy.gurps.rules.modifiers.SummandModifier;
import net.mortiy.gurps.rules.table.Rolls;

/**
 * You have a penalty to all DX, IQ, skill, and self-control rolls.
 * This is -2 for Moderate Pain, -4 for Severe Pain, and -6 for Terrible Pain.
 * High Pain Threshold halves these penalties; Low Pain Threshold doubles them.
 */
public class Pain extends Affliction {
    enum Severity {
        Modferate(-2),
        Severe(-4),
        Terible(-6);

        private final int modifier;

        Severity(int modifier) {
            this.modifier = modifier;
        }

        int getModifier() {
            return modifier;
        }
    }

    Severity severity;

    protected Pain(Individual character, Severity severity) {
        super(character);
        this.severity = severity;
    }

    @Override
    public int affect() {
        int modifier = this.severity.getModifier();

        if (c.hasTrait("High Pain Threshold")) {
            modifier /= 2;
        }
        if (c.hasTrait("Low Pain Threshold")) {
            modifier *= 2;
        }

        SummandModifier summandModifier = new SummandModifier(modifier);

        addModifier(Attribute.Dexterity, summandModifier);
        addModifier(Attribute.Intelligence, summandModifier);
        addModifier(Rolls.SkillRoll, summandModifier);
        addModifier(Rolls.SelfControlRoll, summandModifier);

        return 0;
    }
}
