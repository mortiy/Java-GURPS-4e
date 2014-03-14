package net.mortiy.gurps.rules.skills.all.meleeweapon.pole;

import net.mortiy.gurps.rules.Individual;
import net.mortiy.gurps.rules.skills.all.meleeweapon.PoleMeleeWeapon;

/**
 * Spear
 * ========================
 * Description:
 * Any long, balanced pole weapon with a thrusting
 * point, including spears, javelins, tridents,
 * and fixed bayonets.
 */
public class Spear extends PoleMeleeWeapon {
    public Spear(Individual individual) {
        super(individual, "Spear");
        try {
            setDefault("Polearm", -4);
            setDefault("Staff", -2);
        } catch (UnknownSkillDefaultSkillException e) {
            e.printStackTrace();
        }
    }
}
