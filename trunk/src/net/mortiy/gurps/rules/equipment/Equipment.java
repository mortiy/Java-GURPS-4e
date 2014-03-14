package net.mortiy.gurps.rules.equipment;
import net.mortiy.gurps.Log;
import net.mortiy.gurps.rules.Individual;
import net.mortiy.gurps.rules.individual.Body;
import net.mortiy.gurps.rules.combat.Damage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class that manages what characters carries in his bag
 * and what is equiped on him in current moment.
 */
public class Equipment extends Container {

    public interface ItemType{};

    private Individual individual;
    private Map<Body.Part, Item> equipment = new HashMap<>();
    public Equipment(Individual individual) {
        this.individual = individual;
    }

    public void putAndEquipItem(Item item, Body.Part bodyPart){
        putItem(item, 1);
        equipItem(item, bodyPart);
    }

    public boolean equipItem(Item item, Body.Part bodyPart){
        if(contains(item)){
            if(item instanceof ArmorItem){
                ArmorItem armorItem = (ArmorItem) item;
                if(!armorItem.isSuitableFor(bodyPart)){
                    Log.w("Equipment", String.format("'%s' can't wear '%s' on '%s'.", individual.getName(), item.getName(), bodyPart));
                    return false;
                }
            }
            equipment.put(bodyPart, item);
            return true;
        }
        Log.w("Equipment", String.format("'%s' doesn't own '%s' to equip it.", individual.getName(), item.getName()));
        return false;
    }


    public boolean unequipItem(Item item){
        for(Body.Part bodyPart : equipment.keySet()){
            if(equipment.get(bodyPart) == item){
                equipment.remove(bodyPart);
                return true;
            }
        }
        return false;
    }

    public boolean unequipItem(Body.Part bodyPart){
        if(equipment.containsKey(bodyPart)){
            equipment.remove(bodyPart);
            return true;
        } else {
            Log.w("Equipment", String.format("'%s' has nothing equipped on '%s'.", individual.getName(), bodyPart.toString()));
            return false;
        }
    }

    public Item getEquipedItem(Body.Part bodyPart){
        return equipment.get(bodyPart);
    }

    public boolean hasEquipped(Class<? extends Item> itemClass) {
        for(Item item : equipment.values()){
            if(itemClass.isInstance(item)){
                return true;
            }
        }
        return false;
    }

    public boolean hasEquipped(Item searchItem) {
        for(Item item : equipment.values()){
            if(searchItem == item){
                return true;
            }
        }
        return false;
    }

    public List<Item> getItemsByType(Class<? extends ItemType> itemClass){
        List<Item> items = new ArrayList<>();
        for(Item item : equipment.values()){
            if(itemClass.isInstance(item)){
                items.add(item);
            }
        }
        return items;
    }


    /**
     * Return character's resistance on given body part for given damage type
     * @param bodyPart Body Part
     * @param damageType Damage Type
     */
    public int getResistance(Body.Part bodyPart, Damage.Type damageType) {
        // TODO: Damage Resistance (DR) calculation
        Item item = getEquipedItem(bodyPart);
        if(item instanceof ArmorItem){
            return ((ArmorItem) item).getDamageResistance(damageType);
        }
        return 0;
    }

}
