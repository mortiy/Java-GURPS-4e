package net.mortiy.gurps.rules.skills.all.meleeweapon.whip;

import net.mortiy.gurps.rules.*;
import net.mortiy.gurps.rules.skills.all.meleeweapon.WhipMeleeWeapon;

/**
 * Kusary
 * =================
 * Description:
 * A weighted chain wielded in two hands.
 */
public class Kusari extends WhipMeleeWeapon {
    public Kusari(net.mortiy.gurps.rules.Character character) {
        super(character, "Kusari", Difficulty.Hard);
        try {
            setDefault("Two-Handed Flail", -4);
        } catch (UnknownSkillDefaultSkillException e) {
            e.printStackTrace();
        }
    }
}
