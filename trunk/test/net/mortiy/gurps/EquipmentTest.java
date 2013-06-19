package net.mortiy.gurps;

import junit.framework.TestCase;
import net.mortiy.gurps.rules.Character;
import net.mortiy.gurps.rules.TechLevel;
import net.mortiy.gurps.rules.character.Body;
import net.mortiy.gurps.rules.character.Encumbrance;
import net.mortiy.gurps.rules.equipment.Equipment;
import net.mortiy.gurps.rules.equipment.Item;
import net.mortiy.gurps.rules.equipment.weapon.all.ShortswordWeapon;

/**
 * Test Character's equipment and encumbrance that it causes
 */
public class EquipmentTest extends TestCase {
    Character character = new Character(Constants.MAX_CHARACTER_POINTS);

    public void testEquipmentEncumbrance() throws Exception {

        Equipment equipment = character.getEquipment();

        equipment.putItem(new Item("Iron Sword", TechLevel.Level.TL0, 0, 10f));
        equipment.putItem(new Item("Holy Book", TechLevel.Level.TL0, 0,5f));

        assertEquals("Check equipment items quantity", 2, equipment.getQuantity());
        assertEquals("Check equipment items weight", 15f, equipment.getTotalWeight());
        assertEquals("Check character Basic Lift", 20f, character.getBasicLift().getValue());

        assertEquals("Character's Encumbrance at Equipment weight 15 lbs.", Encumbrance.No, character.getEncumbrance());

        equipment.putItem(new Item("Light Thing", TechLevel.Level.TL0, 0,10f));
        assertEquals("Ensure equipment weight", 25f, equipment.getTotalWeight());
        assertEquals("Character's Encumbrance at Equipment weight 25 lbs.", Encumbrance.Light, character.getEncumbrance());

        equipment.putItem(new Item("Medium Thing", TechLevel.Level.TL0, 0,20f));
        assertEquals("Ensure equipment weight", 45f, equipment.getTotalWeight());
        assertEquals("Character's Encumbrance at Equipment weight 45 lbs.", Encumbrance.Medium, character.getEncumbrance());

        equipment.putItem(new Item("Heavy Thing", TechLevel.Level.TL0, 0,40f));
        assertEquals("Ensure equipment weight", 85f, equipment.getTotalWeight());
        assertEquals("Character's Encumbrance at Equipment weight 85 lbs.", Encumbrance.Heavy, character.getEncumbrance());

        equipment.putItem(new Item("Very Heavy Thing", TechLevel.Level.TL0, 0,80f));
        assertEquals("Ensure equipment weight", 165f, equipment.getTotalWeight());
        assertEquals("Character's Encumbrance at Equipment weight 165 lbs.", Encumbrance.ExtraHeavy, character.getEncumbrance());


    }

    public void testEquip() throws Exception {
        Equipment equipment = character.getEquipment();

        ShortswordWeapon shortswordWeapon = new ShortswordWeapon();
        equipment.putAndEquipItem(shortswordWeapon, Body.Part.RightHand);
        assertTrue("Check equipment by reference", equipment.hasEquipped(shortswordWeapon));
        assertTrue("Check equipment by class", equipment.hasEquipped(ShortswordWeapon.class));
        assertTrue("Unequip item from right hand", equipment.unequipItem(Body.Part.RightHand));
        assertFalse("Check equipment by reference", equipment.hasEquipped(shortswordWeapon));
    }
}
