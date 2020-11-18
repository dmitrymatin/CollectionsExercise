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
        for (Item it : this.items) {
            if (it instanceof BoxOnSet) {
                if (it.equals(item) && this.items.remove(item)) { // the searched item is the box itself
                    unload(item);
                    return true;
                } else if (((BoxOnSet) it).remove(item)) { // recursively search the box
                    unload(item);
                    return true;
                }
            } else if (it != null && it.equals(item) && this.items.remove(item)) {
                unload(item);
                return true;
            }
        }
        return false;
    }

    public Item remove(int id) {
        for (Item it : this.items) {
            if (it instanceof BoxOnSet) {
                if (it.getId() == id && this.items.remove(it)) { // the searched item is the box itself
                    unload(it);
                    return it;
                } else {
                    Item itemToRemove = ((BoxOnSet) it).remove(id); // recursively search the box
                    if (itemToRemove != null) {
                        unload(itemToRemove);
                        return itemToRemove;
                    }
                }
            } else if (it != null && it.getId() == id && this.items.remove(it)) {
                unload(it);
                return it;
            }
        }
        return null;
    }

    public boolean find(Item item) {
        for (Item it : this.items) {
            if (it instanceof BoxOnSet) { // the searched item is the box itself
                if (it.equals(item))
                    return true;
                else if (((BoxOnSet) it).find(item))
                    return true;
            } else if (it != null && it.equals(item))
                return true;
        }
        return false;
    }

    public Item find(int id) {
        for (Item it : this.items) {
            if (it instanceof BoxOnSet) {
                if (it.getId() == id)
                    return it;
                else {
                    Item itemToFind = ((BoxOnSet) it).find(id);
                    if (itemToFind != null)
                        return itemToFind;
                }
            } else if (it != null && it.getId() == id)
                return it;
        }
        return null;
    }
}

