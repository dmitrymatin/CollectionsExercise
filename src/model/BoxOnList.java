package model;

import java.util.ArrayList;

public class BoxOnList extends Item {
    private ArrayList<Item> items = new ArrayList<>();
    private float maxStorageMass;
    private float storageMass;

    public BoxOnList(String name, float mass, float maxStorageMass) {
        super(name, mass);
        this.maxStorageMass = maxStorageMass;
    }

    public void put(Item item) {
        if (!item.isStored() && !this.isStored)
            if (this.canStore(item) && this.items.add(item)) {
                item.isStored = true;
                this.storageMass += item.mass;
                this.mass += item.mass;
            }
    }

    public boolean remove(Item item) {
        boolean success = false;
        for (Item it : this.items) {
            if (it instanceof BoxOnList)
                success = ((BoxOnList) it).remove(item);
            else if (it != null && it.equals(item) && this.items.remove(item)) {
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
        for (Item it : this.items) {
            if (it instanceof BoxOnList)
                itemToRemove = ((BoxOnList) it).remove(id);
            else if (it != null && it.getId() == id) {
                this.storageMass -= it.mass;
                this.mass -= it.mass;
                itemToRemove = it;
            }
        }
        return itemToRemove;
    }

    public boolean find(Item item) {
        boolean success = false;
        for (Item it : this.items) {
            if (it instanceof BoxOnList)
                success = ((BoxOnList) it).find(item);
            else if (it != null && it.equals(item))
                return true;
        }
        return success;
    }

    public Item find(int id) {
        Item itemToFind = null;
        for (Item it : this.items) {
            if (it instanceof BoxOnList)
                itemToFind = ((BoxOnList) it).find(id);
            else if (it != null && it.getId() == id)
                itemToFind = it;
        }
        return itemToFind;
    }

    private boolean canStore(Item item) {
        return this.storageMass + item.mass <= this.maxStorageMass;
    }

//    public ArrayList<Item> getItems() {
//        return items;
//    }

    public float getMaxStorageMass() {
        return maxStorageMass;
    }

    public float getStorageMass() {
        return storageMass;
    }
}
