package net.mortiy.gurps;

import junit.framework.TestCase;
import net.mortiy.gurps.rules.Character;
import net.mortiy.gurps.rules.equipment.weapon.all.ShortswordWeapon;
import net.mortiy.gurps.rules.equipment.weapon.statistics.MusclePoweredMeleeWeaponMode;
import net.mortiy.gurps.rules.equipment.weapon.statistics.MusclePoweredDamage;
import net.mortiy.gurps.rules.table.RollFormula;

/**
 * Created with IntelliJ IDEA.
 * User: oleksandr.sidko
 * Date: 22.12.12
 * Time: 14:36
 * To change this template use File | Settings | File Templates.
 */
public class WeaponTest extends TestCase {
    public void testMusclePoweredWeapon() throws Exception {
        Character character = new Character(250);
        character.setName("Oleksandr Sid");

        ShortswordWeapon sword = new ShortswordWeapon();

        // TODO: Refactor damage type:
        MusclePoweredMeleeWeaponMode swordMode = sword.getActiveWeaponMode();
        assertEquals("Check that default damage type for Shortsword is <Swinging>", MusclePoweredDamage.Type.Swinging, swordMode.getBasicType());
        assertEquals("Modifier for Shortsword <Swinging> damage type is +2", swordMode.getWeaponDamage().getDamageFormula().getModifier(), 0);

        RollFormula modifierDamageFormula = sword.getDamageFormula(character);
        assertEquals("Shortsword Swinging damage for character with Strength 10 should be <1d>", new RollFormula(1), modifierDamageFormula);

    }
}
