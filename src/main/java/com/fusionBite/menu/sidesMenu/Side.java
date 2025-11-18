package com.fusionBite.menu.sidesMenu;

import com.fusionBite.menu.order.Orderable;

public class Side implements Orderable {
    private String name;
    private double price;

    public Side(String name,double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public double getPrice() {
        return price;
    }

    public String getDescription(){
        return String.format("%s - $%.2f", name, price);
    }

    public void setPrice(double price) {
        this.price = price;
    }
    @Override
    public String toString() {
        return String.format("%s ($%.2f)", name, price);
    }
}
