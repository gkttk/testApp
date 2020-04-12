package org.testApp;

public class Theme {
    private int id;
    private String name;
    private int id_owner;

    public Theme(int id, String name, int id_owner) {
        this.id = id;
        this.name = name;
        this.id_owner = id_owner;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getId_owner() {
        return id_owner;
    }


    @Override
    public String toString() {
        return id + " - " + name + " - " + id_owner;
    }
}
