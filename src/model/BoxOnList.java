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
        if (this.canStore(item) && this.items.add(item))
            loadItem(item);
    }

    public boolean remove(Item item) {
        boolean success = false;
        for (Item it : this.items) {
            if (it instanceof BoxOnList) {
                if (it.equals(item) && this.items.remove(item)) { // the searched item is the box itself
                    unload(item);
                    return true;
                } else
                    success = ((BoxOnList) it).remove(item); // recursively search the box
            } else if (it != null && it.equals(item) && this.items.remove(item)) {
                unload(item);
                return true;
            }
        }
        return success;
    }

    public Item remove(int id) {
        Item itemToRemove = null;
        for (Item it : this.items) {
            if (it instanceof BoxOnList) {
                if (it.getId() == id) { // the searched item is the box itself
                    itemToRemove = it;
                    unload(itemToRemove);
                    return itemToRemove;
                } else
                    itemToRemove = ((BoxOnList) it).remove(id); // recursively search the box
            } else if (it != null && it.getId() == id && this.items.remove(it)) {
                itemToRemove = it;
                unload(itemToRemove);
                return itemToRemove;
            }
        }
        return itemToRemove;
    }

    public boolean find(Item item) {
        boolean success = false;
        for (Item it : this.items) {
            if (it instanceof BoxOnList) { // the searched item is the box itself
                if (it.equals(item))
                    return true;
                else
                    success = ((BoxOnList) it).find(item);
            } else if (it != null && it.equals(item))
                return true;
        }
        return success;
    }

    public Item find(int id) {
        Item itemToFind = null;
        for (Item it : this.items) {
            if (it instanceof BoxOnList) {
                if (it.getId() == id)
                    return it;
                else
                    itemToFind = ((BoxOnList) it).find(id);
            } else if (it != null && it.getId() == id)
                return it;
        }
        return itemToFind;
    }

    private boolean canStore(Item item) {
        if (!this.isStored    // make sure the box is root item (otherwise it needs to be removed first)
                && !this.equals(item)       // make sure the box cannot store itself
                && !item.isStored) // make sure item can only be stored in one place at a given moment
            return this.storageMass + item.mass <= this.maxStorageMass;
        else return false;
    }

    private void loadItem(Item item) {
        item.isStored = true;
        this.storageMass += item.mass;
        this.mass += item.mass;
    }

    private void unload(Item item) {
        item.isStored = false;
        this.storageMass -= item.mass;
        this.mass -= item.mass;
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
