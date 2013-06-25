package net.mortiy.gurps.rules.equipment;
import net.mortiy.gurps.Log;
import net.mortiy.gurps.rules.Character;
import net.mortiy.gurps.rules.character.Body;

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

    private Character character;
    private Map<Body.Part, Item> equipment = new HashMap<>();
    public Equipment(Character character) {
        this.character = character;
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
                    Log.w("Equipment", String.format("'%s' can't wear '%s' on '%s'.", character.getName(), item.getName(), bodyPart));
                    return false;
                }
            }
            equipment.put(bodyPart, item);
            return true;
        }
        Log.w("Equipment", String.format("'%s' doesn't own '%s' to equip it.", character.getName(), item.getName()));
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
            Log.w("Equipment", String.format("'%s' has nothing equipped on '%s'.", character.getName(), bodyPart.toString()));
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

}
