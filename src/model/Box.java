package model;

public abstract class Box extends Item {
    protected float maxStorageMass;
    protected float storageMass;

    public Box(String name, float mass, float maxStorageMass) {
        super(name, mass);
        this.maxStorageMass = maxStorageMass;
    }

    public abstract void put(Item item);
    public abstract boolean remove(Item item);
    public abstract Item remove(int id);
    public abstract boolean find(Item item);
    public abstract Item find(int id);

    protected boolean canStore(Item item) {
        if (!this.isStored    // make sure the box is root item (otherwise it needs to be removed first)
                && !this.equals(item)       // make sure the box cannot store itself
                && !item.isStored) // make sure item can only be stored in one place at a given moment
            return this.storageMass + item.mass <= this.maxStorageMass;
        else return false;
    }

    protected void loadItem(Item item) {
        item.isStored = true;
        this.storageMass += item.mass;
        this.mass += item.mass;
    }

    protected void unload(Item item) {
        item.isStored = false;
        this.storageMass -= item.mass;
        this.mass -= item.mass;
    }

    public float getMaxStorageMass() {
        return maxStorageMass;
    }

    public float getStorageMass() {
        return storageMass;
    }
}
