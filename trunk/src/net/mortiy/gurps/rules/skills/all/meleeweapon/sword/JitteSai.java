package net.mortiy.gurps.rules.skills.all.meleeweapon.sword;

import net.mortiy.gurps.rules.Individual;
import net.mortiy.gurps.rules.skills.all.meleeweapon.SwordMeleeWeapon;

/**
 * Jitte/Sai
 * ========================
 * Description:
 * Any tined, one-handed sword designed to catch
 * rigid weapons. Jitte/Sai weapons are
 * built for disarming, and give +2 in the
 * Quick Contest to disarm an opponent
 * (see Knocking a Weapon Away, p. 401).
 * Furthermore, if you attempt to disarm
 * on the turn immediately after you
 * parry your opponent’s weapon, you
 * need not roll to hit his weapon first.
 * Just state that you are attempting to
 * disarm and move directly to the Quick
 * Contest! This still counts as an attack.
 */
public class JitteSai extends SwordMeleeWeapon {

    public JitteSai(Individual individual) {
        super(individual, "Jitte/Sai");
        try {
            setDefault("Force Sword", -4);
            setDefault("Main-Gauche", -4);
            setDefault("Shortsword", -3);
        } catch (UnknownSkillDefaultSkillException e) {
            e.printStackTrace();
        }

    }
}
