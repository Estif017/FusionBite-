package com.fusionBite.menu.drinksMenu;

public class Drink {
    private String size;
    private String flavor;
    private double price;

    public Drink(String size, String flavor,double price) {
        this.size = size;
        this.flavor = flavor;
        this.price = price;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getFlavor() {
        return flavor;
    }

    public void setFlavor(String flavor) {
        this.flavor = flavor;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
