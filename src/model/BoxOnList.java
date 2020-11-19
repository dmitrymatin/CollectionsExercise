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
        for (Item it : this.items) {
            if (it != null && it.equals(item) && this.items.remove(item)) {
                unload(item);
                return true;
            }
            if (it instanceof BoxOnList) {
                if (((BoxOnList) it).remove(item)) { // recursively search the box
                    unload(item);
                    return true;
                }
            }
        }
        return false;
    }

    public Item remove(int id) {
        for (Item it : this.items) {
            if (it != null && it.getId() == id && this.items.remove(it)) {
                unload(it);
                return it;
            }
            if (it instanceof BoxOnList) {
                Item itemToRemove = ((BoxOnList) it).remove(id); // recursively search the box
                if (itemToRemove != null) {
                    unload(itemToRemove);
                    return itemToRemove;
                }
            }
        }
        return null;
    }

    public boolean find(Item item) {
        for (Item it : this.items) {
            if (it instanceof BoxOnList) { // the searched item is the box itself
                if (it.equals(item))
                    return true;
                else if (((BoxOnList) it).find(item))
                    return true;
            } else if (it != null && it.equals(item))
                return true;
        }
        return false;
    }


    public Item find(int id) {
        for (Item it : this.items) {
            if (it instanceof BoxOnList) {
                if (it.getId() == id)
                    return it;
                else {
                    Item itemToFind = ((BoxOnList) it).find(id);
                    if (itemToFind != null)
                        return itemToFind;
                }
            } else if (it != null && it.getId() == id)
                return it;
        }
        return null;
    }
}
