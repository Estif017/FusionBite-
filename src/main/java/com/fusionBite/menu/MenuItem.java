package com.fusionBite.menu;

import java.util.ArrayList;
import java.util.List;

public abstract class MenuItem {
    private String name;
    private String size;//
    private String type;//crust for pizza, bread for sandwich, shell for taco
    private List<String> meats;
    private List<String> cheeses;
    private List<String> regularToppings;
    private List<String> sauces;
    private boolean isSpecial; //stuffed crust, toasted...
    private double basePrice;
    private double totalPrice;

    public MenuItem(String name, String size, String type, double basePrice) {
        this.name = name;
        this.size = size;
        this.type = type;
        this.basePrice = basePrice;
        this.meats = new ArrayList<>();
        this.cheeses = new ArrayList<>();
        this.regularToppings = new ArrayList<>();
        this.sauces = new ArrayList<>();
        this.isSpecial = false;
        this.totalPrice = basePrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    //--- customizations----
    public  void addMeats(String meat,double price){
        meats.add(meat);
        totalPrice+=price;
    }

    public void addCheese(String cheese, double price){
        cheeses.add(cheese);
        totalPrice+=price;
    }

    public void addRegularTopping(String topping){
        regularToppings.add(topping);
    }

    public void addSauce(String sauce){
        sauces.add(sauce);
    }

    public void applySpecial(double extraCoast){
        isSpecial = true;
        totalPrice+=extraCoast;
    }

    public abstract void calculateTotal();

    //getters and setters

    public String getName() { return name; }
    public String getSize() { return size; }
    public String getType() { return type; }
    public List<String> getMeats() { return meats; }
    public List<String> getCheeses() { return cheeses; }
    public List<String> getRegularToppings() { return regularToppings; }
    public List<String> getSauces() { return sauces; }
    public boolean isSpecial() { return isSpecial; }
    public double getBasePrice() { return basePrice; }
    public double getTotalPrice() { return totalPrice; }

    public void setSize(String size) { this.size = size; }
    public void setType(String type) { this.type = type; }
    public void setSpecial(boolean special) { isSpecial = special; }

    public void displayDetails() {
        System.out.println("\n--- " + name.toUpperCase() + " DETAILS ---");
        System.out.println("Size: " + size);
        System.out.println("Type: " + type);
        System.out.println("Meats: " + String.join(", ", meats));
        System.out.println("Cheeses: " + String.join(", ", cheeses));
        System.out.println("Regular Toppings: " + String.join(", ", regularToppings));
        System.out.println("Sauces: " + String.join(", ", sauces));
        System.out.println("Special: " + (isSpecial ? "Yes" : "No"));
        System.out.printf("Total Price: $%.2f\n", totalPrice);
    }

}
