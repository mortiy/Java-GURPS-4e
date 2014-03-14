package net.mortiy.gurps.rules.skills.all.meleeweapon.sword;

import net.mortiy.gurps.rules.Individual;
import net.mortiy.gurps.rules.skills.all.meleeweapon.SwordMeleeWeapon;

/**
 * Knife
 * ========================
 * Description:
 * Any rigid, hilted blade less than one foot long, from a
 * pocketknife to a bowie knife.
 * A knife has a very small parrying surface,
 * which gives you -1 to your Parry score.
 */
public class Knife extends SwordMeleeWeapon {

    public Knife(Individual individual) {
        super(individual, "Knife", Difficulty.Easy);
        try {
            setDefault("Force Sword", -3);
            setDefault("Main-Gauche", -3);
            setDefault("Shortsword", -3);
        } catch (UnknownSkillDefaultSkillException e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getParry() {
        return super.getParry() - 1;
    }
}
