package net.mortiy.gurps.rules.equipment;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: oleksandr.sidko
 * Date: 09.12.12
 * Time: 16:52
 * To change this template use File | Settings | File Templates.
 */
public class Container {
    public class Items {
        Item item;
        int quantity;

        Items(Item item, int quantity) {
            this.item = item;
            this.quantity = quantity;
        }

        public Item getItem() {
            return item;
        }

        public int getQuantity() {
            return quantity;
        }
    }

    Map<Item, Integer> quantities = new HashMap<Item, Integer>();
    Map<String, Item> items = new HashMap<String, Item>();


    public Items getItem(String itemKey) {
        Item item = items.get(itemKey);
        return new Items(item, quantities.get(item));
    }


    public void putItem(Item item){
        putItem(item, 1);
    }

    public void putItem(Item item , int quantity){
        if(!items.containsKey(item)){
            items.put(item.getKey(), item);
            quantities.put(item, quantity);
        } else {
            Integer currentQuantity = quantities.get(item);
            currentQuantity += quantity;
            quantities.put(item, currentQuantity);
        }


    }

    public void removeItem(Item item){
        removeItem(item, 1);
    }

    public void removeItem(Item item, int quantity){
        Integer currentQuantity = quantities.get(item);
        if(quantity > currentQuantity){
            return;
        }
        currentQuantity -= quantity;
        if(currentQuantity <= 0){
            items.remove(item.getKey());
            quantities.remove(item);
        } else {
            quantities.put(item, currentQuantity);
        }

    }

    public boolean contains(Item item){
        return quantities.containsKey(item);
    }

    public void removeAll(Item item){
        items.remove(item);
    }

    public int getQuantity(){
        return items.size();
    }

    public float getTotalWeight(){
        float totalWeight = 0.0f;
        for(Item item : items.values()){
            totalWeight += quantities.get(item) * item.getWeight();
        }
        return totalWeight;
    }

    public void clear(){
        items.clear();
    }
}
