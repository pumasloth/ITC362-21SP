package com.missouristate.penrose.todolistex3;

public class Item {
    private int id;
    private String itemName;

    public Item(int id, String name) {
        setID(id);
        setItemName(name);
    }

    private void setID(int newId) {
        this.id = newId;
    }

    private void setItemName(String newName) {
        this.itemName = newName;
    }

    public int getId() {
        return this.id;
    }

    public String getItemName() {
        return this.itemName;
    }
}
