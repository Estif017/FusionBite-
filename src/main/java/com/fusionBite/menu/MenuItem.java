package com.fusionBite.menu;

public class MenuItem {
    private String size;//
    private String type;//crust for pizza, bread for sandwich, shell for taco
    private String[] meets;
    private String[] cheese;
    private String[] regularToppings;
    private String[] sauces;

    public MenuItem(String size, String type, String[] meets, String[] cheese, String[] regularToppings, String[] sauces) {
        this.size = size;
        this.type = type;
        this.meets = meets;
        this.cheese = cheese;
        this.regularToppings = regularToppings;
        this.sauces = sauces;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String[] getMeets() {
        return meets;
    }

    public void setMeets(String[] meets) {
        this.meets = meets;
    }

    public String[] getCheese() {
        return cheese;
    }

    public void setCheese(String[] cheese) {
        this.cheese = cheese;
    }

    public String[] getRegularToppings() {
        return regularToppings;
    }

    public void setRegularToppings(String[] regularToppings) {
        this.regularToppings = regularToppings;
    }

    public String[] getSauces() {
        return sauces;
    }

    public void setSauces(String[] sauces) {
        this.sauces = sauces;
    }
}
