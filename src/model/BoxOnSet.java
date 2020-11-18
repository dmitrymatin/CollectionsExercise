package model;

import java.util.HashSet;

public class BoxOnSet extends Box {
    private HashSet<Item> items = new HashSet<>();

    public BoxOnSet(String name, float mass, float maxStorageMass) {
        super(name, mass, maxStorageMass);
    }

    public void put(Item item) {
        if (!item.isStored() && !this.isStored)
            if (this.canStore(item) && this.items.add(item)) {
                loadItem(item);
            }
    }

    public boolean remove(Item item) {
        boolean success = false;
        for (Item it : this.items) {
            if (it instanceof BoxOnSet) {
                if (it.equals(item) && this.items.remove(item)) { // the searched item is the box itself
                    unload(item);
                    return true;
                } else
                    success = ((BoxOnSet) it).remove(item); // recursively search the box
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
            if (it instanceof BoxOnSet) {
                if (it.getId() == id) { // the searched item is the box itself
                    itemToRemove = it;
                    itemToRemove.isStored = false;
                    this.storageMass -= itemToRemove.mass;
                    this.mass -= itemToRemove.mass;
                    return itemToRemove;
                } else
                    itemToRemove = ((BoxOnSet) it).remove(id); // recursively search the box
            } else if (it != null && it.getId() == id && this.items.remove(it)) {
                itemToRemove = it;
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
        for (Item it : this.items) {
            if (it instanceof BoxOnSet) { // the searched item is the box itself
                if (it.equals(item))
                    return true;
                else
                    success = ((BoxOnSet) it).find(item);
            }
            else if (it != null && it.equals(item))
                return true;
        }
        return success;
    }

    public Item find(int id) {
        Item itemToFind = null;
        for (Item it : this.items) {
            if (it instanceof BoxOnSet){
                if (it.getId() == id)
                    return it;
                else
                    itemToFind = ((BoxOnSet) it).find(id);
            }
            else if (it != null && it.getId() == id)
                return it;
        }
        return itemToFind;
    }
}

