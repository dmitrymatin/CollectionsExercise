package model;

public class Item {
    private static int id;
    protected String name;
    protected float mass;
    protected boolean isStored = false;

    public Item(String name, float mass) {
        Item.id++;
        this.name = name;
        this.mass = mass;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getMass() {
        return mass;
    }

    public boolean isStored() {
        return isStored;
    }
}