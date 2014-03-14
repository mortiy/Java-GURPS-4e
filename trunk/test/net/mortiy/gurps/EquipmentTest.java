package net.mortiy.gurps;

import junit.framework.TestCase;
import net.mortiy.gurps.rules.Individual;
import net.mortiy.gurps.rules.TechLevel;
import net.mortiy.gurps.rules.individual.Body;
import net.mortiy.gurps.rules.individual.Encumbrance;
import net.mortiy.gurps.rules.equipment.Equipment;
import net.mortiy.gurps.rules.equipment.Item;
import net.mortiy.gurps.rules.equipment.ShieldItem;
import net.mortiy.gurps.rules.equipment.all.SmallShield;
import net.mortiy.gurps.rules.equipment.weapon.all.ShortswordWeapon;

/**
 * Test Character's equipment and encumbrance that it causes
 */
public class EquipmentTest extends TestCase {
    Individual individual = new Individual(Constants.MAX_CHARACTER_POINTS);

    public void testEquipmentEncumbrance() throws Exception {

        Equipment equipment = individual.getEquipment();

        equipment.putItem(new Item("Iron Sword", TechLevel.TL0, 0, 10f));
        equipment.putItem(new Item("Holy Book", TechLevel.TL0, 0,5f));

        assertEquals("Check equipment items quantity", 2, equipment.getQuantity());
        assertEquals("Check equipment items weight", 15f, equipment.getTotalWeight());
        assertEquals("Check individual Basic Lift", 20f, individual.getBasicLift().getValue());

        assertEquals("Individual's Encumbrance at Equipment weight 15 lbs.", Encumbrance.No, individual.getEncumbrance());

        equipment.putItem(new Item("Light Thing", TechLevel.TL0, 0,10f));
        assertEquals("Ensure equipment weight", 25f, equipment.getTotalWeight());
        assertEquals("Individual's Encumbrance at Equipment weight 25 lbs.", Encumbrance.Light, individual.getEncumbrance());

        equipment.putItem(new Item("Medium Thing", TechLevel.TL0, 0,20f));
        assertEquals("Ensure equipment weight", 45f, equipment.getTotalWeight());
        assertEquals("Individual's Encumbrance at Equipment weight 45 lbs.", Encumbrance.Medium, individual.getEncumbrance());

        equipment.putItem(new Item("Heavy Thing", TechLevel.TL0, 0,40f));
        assertEquals("Ensure equipment weight", 85f, equipment.getTotalWeight());
        assertEquals("Individual's Encumbrance at Equipment weight 85 lbs.", Encumbrance.Heavy, individual.getEncumbrance());

        equipment.putItem(new Item("Very Heavy Thing", TechLevel.TL0, 0,80f));
        assertEquals("Ensure equipment weight", 165f, equipment.getTotalWeight());
        assertEquals("Individual's Encumbrance at Equipment weight 165 lbs.", Encumbrance.ExtraHeavy, individual.getEncumbrance());


    }

    public void testEquipPossibility(){

        Individual c1 = new Individual(100);
        c1.setName("Alpha");
        Individual c2 = new Individual(100);
        c1.setName("Beta");

        ShortswordWeapon sword = new ShortswordWeapon();
        ShieldItem smallShield = new SmallShield();

        c1.getEquipment().putItem(sword);
        assertTrue("Individual can equip item that he owns", c1.equip(sword, Body.Part.RightHand));
        assertFalse("Individual can't equip item that he doesn't own", c1.equip(smallShield, Body.Part.RightHand));

        // TODO: Prevent same item be able to put in different equipments:
        c2.getEquipment().putItem(sword);


    }

    public void testEquip() throws Exception {
        Equipment equipment = individual.getEquipment();

        ShortswordWeapon shortswordWeapon = new ShortswordWeapon();
        equipment.putAndEquipItem(shortswordWeapon, Body.Part.RightHand);
        assertTrue("Check equipment by reference", equipment.hasEquipped(shortswordWeapon));
        assertTrue("Check equipment by class", equipment.hasEquipped(ShortswordWeapon.class));
        assertTrue("Unequip item from right hand", equipment.unequipItem(Body.Part.RightHand));
        assertFalse("Check equipment by reference", equipment.hasEquipped(shortswordWeapon));
    }
}
