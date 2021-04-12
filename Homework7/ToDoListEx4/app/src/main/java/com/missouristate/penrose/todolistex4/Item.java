package com.missouristate.penrose.todolistex4;

public class Item {
    private int id;
    private String itemName;
    private String dueDate;

    public Item(int id, String name, String dueDate) {
        setID(id);
        setItemName(name);
        setDueDate(dueDate);
    }

    private void setID(int newId) {
        this.id = newId;
    }

    private void setItemName(String newName) {
        this.itemName = newName;
    }

    private void setDueDate(String newDueDate) {
        this.dueDate = newDueDate;
    }

    public int getId() {
        return this.id;
    }

    public String getItemName() {
        return this.itemName;
    }

    public String getDueDate() { return this.dueDate; }
}
