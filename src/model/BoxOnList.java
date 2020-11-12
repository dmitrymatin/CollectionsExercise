package model;

import java.util.ArrayList;

public class BoxOnList extends Item {
    private ArrayList<Item> items = new ArrayList<Item>();
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
        for (Item it : this.items) {
            if (it instanceof BoxOnList)
                return ((BoxOnList) it).remove(item);
            else if (it != null && it.equals(item) && this.items.remove(item)) {
                item.isStored = false;
                this.storageMass -= item.mass;
                this.mass -= item.mass;
                return true;
            }
        }
        return false;
    }

    public Item remove(int id) {
        for (Item it : this.items) {
            if (it instanceof BoxOnList)
                return ((BoxOnList) it).remove(id);
            else if (it != null && it.getId() == id) {
                Item item = this.items.remove(id);
                this.storageMass -= item.mass;
                this.mass -= item.mass;
            }
        }
        return null;
    }

    public boolean find(Item item) {
        for (Item it : this.items) {
            if (it instanceof BoxOnList)
                return ((BoxOnList) it).find(item);
            else if (it != null && it.equals(item))
                return true;
        }
        return false;
    }

    public Item find(int id) {
        for (Item it : this.items) {
            if (it instanceof BoxOnList)
                return ((BoxOnList) it).find(id);
            else if (it != null && it.getId() == id)
                return it;
        }
        return null;
    }

    private boolean canStore(Item item) {
        if (this.storageMass + item.mass <= this.maxStorageMass)
            return true;
        else
            return false;
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
