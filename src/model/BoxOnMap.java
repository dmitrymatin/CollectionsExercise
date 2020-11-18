package model;

import java.util.HashMap;

public class BoxOnMap extends Box {
    private HashMap<Integer, Item> items = new HashMap<>();

    public BoxOnMap(String name, float mass, float maxStorageMass) {
        super(name, mass, maxStorageMass);
    }

    public void put(Item item) {
        if (!item.isStored() && !this.isStored)
            if (this.canStore(item)) {
                this.items.put(item.getId(), item);
                loadItem(item);
            }
    }

    public boolean remove(Item item) {
        for (Integer key : this.items.keySet()) {
            Item currentItem = this.items.get(key);
            if (currentItem instanceof BoxOnMap) {
                if (currentItem.equals(item)) { // the searched item is a box itself
                    unload(this.items.remove(key));
                    return true;
                } else if (((BoxOnMap) currentItem).remove(item)) {// recursively search the box
                    unload(this.items.remove(key));
                    return true;
                }
            } else if (currentItem.equals(item)) {
                unload(this.items.remove(key));
                return true;
            }
        }
        return false;
    }

    public Item remove(int id) {
        for (Integer key : this.items.keySet()) {
            Item currentItem = this.items.get(key);
            if (currentItem instanceof BoxOnMap) {
                if (currentItem.getId() == id) { // the searched item is a box itself
                    Item itemToRemove = this.items.remove(key);
                    unload(itemToRemove);
                    return itemToRemove;
                } else {
                    Item itemToRemove = ((BoxOnMap) currentItem).remove(id); // recursively search the box
                    if (itemToRemove != null) {
                        unload(itemToRemove);
                        return itemToRemove;
                    }
                }
            } else if (currentItem.getId() == id) {
                Item itemToRemove = this.items.remove(key);
                unload(itemToRemove);
                return itemToRemove;
            }
        }
        return null;
    }

    public boolean find(Item item) {
        for (Integer key : this.items.keySet()) {
            Item currentItem = this.items.get(key);

            if (currentItem instanceof BoxOnMap) { // the searched item is the box itself
                if (currentItem.equals(item))
                    return true;
                else if (((BoxOnMap) currentItem).find(item))
                    return true;
            } else if (currentItem.equals(item))
                return true;
        }
        return false;
    }

    public Item find(int id) {
        for (Integer key : this.items.keySet()) {
            Item currentItem = this.items.get(key);

            if (currentItem instanceof BoxOnMap) {
                if (currentItem.getId() == id)
                    return currentItem;
                else {
                    Item searchedItem = ((BoxOnMap) currentItem).find(id);
                    if (searchedItem != null)
                        return searchedItem;
                }
            } else if (currentItem.getId() == id)
                return currentItem;
        }
        return null;
    }
}

