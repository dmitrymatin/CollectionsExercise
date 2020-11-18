package model;

public class Item {
    private static int count;
    private int id;

    protected String name;
    protected float mass;
    protected boolean isStored = false;

    public Item(String name, float mass) {
        id = ++Item.count;

        System.out.println(name + "-" + id); // for debug purposes
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
