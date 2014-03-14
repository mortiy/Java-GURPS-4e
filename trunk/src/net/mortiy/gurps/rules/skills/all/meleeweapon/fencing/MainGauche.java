package net.mortiy.gurps.rules.skills.all.meleeweapon.fencing;

import net.mortiy.gurps.rules.Individual;
import net.mortiy.gurps.rules.skills.all.meleeweapon.FencingMeleeWeapon;

/**
 * Main-Gauche
 * ======================
 * Description:
 * Any weapon normally wielded with Knife
 * or Jitte/Sai skill (see below), used in
 * the “off” hand. With this skill, you may
 * ignore the penalty for using the “off”
 * hand on defense (attacks are still at -4)
 * and the -1 for parrying with a knife. To
 * wield a knife as a primary weapon, use
 * Knife skill. Defaults
 */
public class MainGauche extends FencingMeleeWeapon {
    public MainGauche(Individual individual) {
        super(individual, "Main-Gauche");
        try {
            setDefault("Jitte/Sai", -4);
            setDefault("Knife", -4);
        } catch (UnknownSkillDefaultSkillException e) {
            e.printStackTrace();
        }
    }
}
