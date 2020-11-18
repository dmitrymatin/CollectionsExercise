package model;

import java.util.HashMap;

public class BoxOnMap extends Item {
    private HashMap<Integer, Item> items = new HashMap<>();
    private float maxStorageMass;
    private float storageMass;

    public BoxOnMap(String name, float mass, float maxStorageMass) {
        super(name, mass);
        this.maxStorageMass = maxStorageMass;
    }

    public void put(Item item) {
        if (!item.isStored() && !this.isStored)
            if (this.canStore(item)) {
                this.items.put(item.getId(), item);

                item.isStored = true;
                this.storageMass += item.mass;
                this.mass += item.mass;
            }
    }

    public boolean remove(Item item) {
        boolean success = false;
        for (Integer key : this.items.keySet()) {
            Item currentItem = this.items.get(key);
            if (currentItem instanceof BoxOnMap) {
                if (currentItem.equals(item)) { // the searched item is the box itself
                    this.items.remove(key);

                    item.isStored = false;
                    this.storageMass -= item.mass;
                    this.mass -= item.mass;
                    return true;
                } else
                    success = ((BoxOnMap) currentItem).remove(item); // recursively search the box
            } else if (currentItem != null && currentItem.equals(item)) {
                this.items.remove(key);

                item.isStored = false;
                this.storageMass -= item.mass;
                this.mass -= item.mass;
                return true;
            }
        }
        return success;
    }

    public Item remove(int id) {
        Item itemToRemove = null;
        for (Integer key : this.items.keySet()) {
            Item currentItem = this.items.get(key);

            if (currentItem instanceof BoxOnMap) {
                if (currentItem.getId() == id) { // the searched item is the box itself
                    itemToRemove = this.items.remove(key);

                    itemToRemove.isStored = false;
                    this.storageMass -= itemToRemove.mass;
                    this.mass -= itemToRemove.mass;
                    return itemToRemove;
                } else
                    itemToRemove = ((BoxOnMap) currentItem).remove(id); // recursively search the box
            } else if (key != null && currentItem.getId() == id) {
                itemToRemove = this.items.remove(key);

                itemToRemove.isStored = false;
                this.storageMass -= itemToRemove.mass;
                this.mass -= itemToRemove.mass;

                return itemToRemove;
            }
        }
        return itemToRemove;
    }

    public boolean find(Item item) {
        boolean success = false;
        for (Integer key : this.items.keySet()) {
            Item currentItem = this.items.get(key);

            if (currentItem instanceof BoxOnMap) { // the searched item is the box itself
                if (currentItem.equals(item))
                    return true;
                else
                    success = ((BoxOnMap) currentItem).find(item);
            }
            else if (key != null && currentItem.equals(item))
                return true;
        }
        return success;
    }

    public Item find(int id) {
        Item itemToFind = null;
        for (Integer key : this.items.keySet()) {
            Item currentItem = this.items.get(key);

            if (currentItem instanceof BoxOnMap){
                if (currentItem.getId() == id)
                    return currentItem;
                else
                    itemToFind = ((BoxOnMap) currentItem).find(id);
            }
            else if (key != null && currentItem.getId() == id)
                return currentItem;
        }
        return itemToFind;
    }

    private boolean canStore(Item item) {
        return this.storageMass + item.mass <= this.maxStorageMass;
    }

    public float getMaxStorageMass() {
        return maxStorageMass;
    }

    public float getStorageMass() {
        return storageMass;
    }
}

