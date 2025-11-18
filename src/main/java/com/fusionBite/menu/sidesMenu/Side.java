package com.fusionBite.menu.sidesMenu;

public class Side {
    private String name;
    private double price;

    public Side(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    @Override
    public String toString() {
        return String.format("%s ($%.2f)", name, price);
    }
}
