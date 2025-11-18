package com.fusionBite.menu.drinksMenu;

import com.fusionBite.menu.order.Orderable;

public class Drink  implements Orderable {
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

    @Override
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String getDescription() {
        return String.format("%s %s Drink - $%.2f",
                size, flavor, price);
    }

}
