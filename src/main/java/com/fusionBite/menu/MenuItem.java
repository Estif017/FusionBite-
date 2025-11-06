package com.fusionBite.menu;

public class MenuItem {
    private String size;
    private String type;
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


}
