package com.missouristate.penrose.candystore;

public class Candy {

    private int id;
    private String name;
    private double price;

    public Candy(int newId, String newName, double newPrice) {
        setID(newId);
        setName(newName);
        setPrice(newPrice);
    }

    private void setPrice(double newPrice) {
        if (newPrice >= 0.0) {
            this.price = newPrice;
        }
    }

    private void setName(String newName) {
        this.name = newName;
    }

    private void setID(int newId) {
        this.id = newId;
    }

    public String getName() {
        return this.name;
    }

    public double getPrice() {
        return this.price;
    }

    public String toString() {
        return this.id + "; " + this.name + "; " + this.price;
    }
}
