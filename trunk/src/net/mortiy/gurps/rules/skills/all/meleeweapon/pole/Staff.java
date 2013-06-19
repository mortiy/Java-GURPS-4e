package net.mortiy.gurps.rules.skills.all.meleeweapon.pole;

import net.mortiy.gurps.rules.Character;
import net.mortiy.gurps.rules.skills.all.meleeweapon.PoleMeleeWeapon;

/**
 * Staff
 * ========================
 * Description:
 * Any long, balanced pole without a striking head.
 * This skill makes good use of the staff’s
 * extensive parrying surface when
 * defending, giving +2 to your Parry
 * score.
 */
public class Staff extends PoleMeleeWeapon {
    public Staff(Character character) {
        super(character, "Staff");
        try {
            setDefault("Polearm", -4);
            setDefault("Spear", -2);
        } catch (UnknownSkillDefaultSkillException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getParry() {
        return super.getParry() + 2;    //To change body of overridden methods use File | Settings | File Templates.
    }
}
