package model;

import java.util.ArrayList;

public class BoxOnList extends Box {
    private ArrayList<Item> items = new ArrayList<>();

    public BoxOnList(String name, float mass, float maxStorageMass) {
        super(name, mass, maxStorageMass);
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
                if (it.getId() == id && this.items.remove(it)) { // the searched item is the box itself
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
}
