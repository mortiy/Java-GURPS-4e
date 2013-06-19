package net.mortiy.gurps.rules.skills.all.meleeweapon.other;

import net.mortiy.gurps.rules.*;
import net.mortiy.gurps.rules.skills.all.MeleeWeapon;

/**
 * Tonfa
 * ====================
 * Description:
 * A tonfa is a baton with a protruding handle on one
 * side. It can function as a baton, but
 * you can also grasp it by the handle and
 * hold it against the forearm in close
 * combat. This grip lets you jab for
 * thrust+1 crushing damage and parry
 * close-combat attacks at (skill/2) + 3,
 * rounded down. Roll vs. skill to change
 * grips. On a success, the grip change is
 * a free action. On a failure, you must
 * spend the entire turn changing grips. A
 * critical failure means you throw your
 * weapon away!
 */
public class Tonfa extends MeleeWeapon {
    public Tonfa(net.mortiy.gurps.rules.Character character) {
        super(character, "Tonfa", Difficulty.Average);
        try {
            setDefault("Shortsword", -3);
        } catch (UnknownSkillDefaultSkillException e) {
            e.printStackTrace();
        }
    }
}
