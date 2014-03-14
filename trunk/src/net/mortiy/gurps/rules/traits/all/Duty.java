package net.mortiy.gurps.rules.traits.all;

import net.mortiy.gurps.rules.Individual;
import net.mortiy.gurps.rules.table.DiceRoller;
import net.mortiy.gurps.rules.table.rolls.SimpleRoll;
import net.mortiy.gurps.rules.traits.Disadvantage;
import net.mortiy.gurps.rules.traits.VariableTrait;
import net.mortiy.gurps.rules.traits.categories.Social;

import java.util.HashSet;
import java.util.Set;

/**
 * Duty
 * =======================
 * If your occupation and social situation saddle you with a significant
 * personal obligation toward others, * and occasionally require you to obey
 * hazardous orders, you have a “Duty.”
 * Duty most often accompanies Rank (p. 29), a Patron (p. 72), or one of the
 * traits discussed under Privilege (p. 30).
 */
public class Duty extends VariableTrait implements Disadvantage, Social {

    public static enum Frequency implements ITraitLevel {

        /**
         * Quite rarely (roll of 6 or less): -2 points.
         */
        QuiteRarely,
        /**
         * Fairly often (roll of 9 or less): -5 points.
         */
        FairlyOften,
        /**
         * Quite often (roll of 12 or less): -10 points
         */
        QuiteOften,
        /**
         * Almost all the time (roll of 15 or less): -15 points.
         * At this level, the GM may rule that you are always on duty.
         */
        Always
    }

    public static enum Hazard {
        None,
        /**
         * Your Duty never requires  you to risk your life. This
         * option is mutually exclusive with Extremely Hazardous.
         * +5 points. (If this raises the cost of your Duty to 0
         * points or more, the obligation is too
         * trivial to qualify as a Duty.)
         */
        Nonhazardous,
        /**
         * Your Duty is enforced by threats to you or your loved ones,
         * or is imposed by exotic mind control, a curse, etc. This is unrelated to how
         * hazardous the Duty is when you carry it out – the danger here lies in what
         * will happen if you don’t carry it out! A Duty can be Involuntary and either
         * Extremely Hazardous or Nonhazardous
         */
        Involuntary,
        /**
         * You are always at risk of death or serious injury when your Duty comes up.
         * There are significant penalties if you refuse to take these risks: dismissal in
         * disgrace, imprisonment, perhaps even death. The GM has the final say as to
         * whether a given Duty is “extremely hazardous” in his campaign.
         */
        ExtremelyHazardous,
    }

    /**
     * Cost of different duty hazards
     */
    private int[] hazardCost = new int[]{0, 5, -5, -5};
    /**
     * Frequency rolls
     */
    private int[] frequencyRoll = new int[]{6, 9, 12, 15};

    /**
     * Set of duty hazards
     */
    private Set<Hazard> hazards = new HashSet<Hazard>();

    public Duty(Individual individual, Frequency frequency) {
        this(individual, frequency, Hazard.None);
    }

    public Duty(Individual individual, Frequency frequency, Hazard hazard) {
        super(individual, "Duty");
        currentLevel = frequency;
        levelsCost = new int[]{-2, -5, -10, -15};
        addHazard(hazard);
    }

    public void addHazard(Hazard hazard){
        hazards.add(hazard);
    }

    public void removeHazard(Hazard hazard){
        hazards.remove(hazard);
    }

    public DiceRoller.RollResult performDutyRoll() {
        int rollAgainst = frequencyRoll[currentLevel.ordinal()];
        return new SimpleRoll(rollAgainst).getResult();
    }

    @Override
    public int getRawCost() {
        int totalCost = 0;
        totalCost += getCost(currentLevel);
        for (Hazard h : hazards) {
            totalCost += hazardCost[h.ordinal()];
        }
        return totalCost;
    }
}
