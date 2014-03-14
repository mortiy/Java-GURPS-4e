package net.mortiy.gurps.rules.skills.all.meleeweapon.fencing;

import net.mortiy.gurps.rules.Individual;
import net.mortiy.gurps.rules.skills.all.meleeweapon.FencingMeleeWeapon;

/**
 * Rapier
 * ========================
 * Description:
 * Any long (over1 yard), light thrusting sword.
 */
public class Rapier extends FencingMeleeWeapon {
    public Rapier(Individual individual) {
        super(individual, "Rapier");
        try {
            setDefault("Broadsword", -4);
        } catch (UnknownSkillDefaultSkillException e) {
            e.printStackTrace();
        }
    }
}
