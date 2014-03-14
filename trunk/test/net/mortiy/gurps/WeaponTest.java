package net.mortiy.gurps;

import junit.framework.TestCase;
import net.mortiy.gurps.rules.Individual;
import net.mortiy.gurps.rules.equipment.weapon.all.ShortswordWeapon;
import net.mortiy.gurps.rules.equipment.weapon.statistics.MusclePoweredMeleeWeaponMode;
import net.mortiy.gurps.rules.equipment.weapon.statistics.MusclePoweredDamage;
import net.mortiy.gurps.rules.table.RollFormula;

public class WeaponTest extends TestCase {
    public void testMusclePoweredWeapon() throws Exception {
        Individual individual = new Individual(250);
        individual.setName("Oleksandr Sid");

        ShortswordWeapon sword = new ShortswordWeapon();

        // TODO: Refactor damage type:
        MusclePoweredMeleeWeaponMode swordMode = sword.getActiveWeaponMode();
        assertEquals("Check that default damage type for Shortsword is <Swinging>", MusclePoweredDamage.Type.Swinging, swordMode.getBasicType());
        assertEquals("Modifier for Shortsword <Swinging> damage type is +2", swordMode.getWeaponDamage().getDamageFormula().getModifier(), 0);

        RollFormula modifierDamageFormula = sword.getDamageFormula(individual);
        assertEquals("Shortsword Swinging damage for individual with Strength 10 should be <1d>", new RollFormula(1), modifierDamageFormula);

    }
}
