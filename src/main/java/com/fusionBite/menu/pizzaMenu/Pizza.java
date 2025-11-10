package com.fusionBite.menu.pizzaMenu;

import com.fusionBite.menu.MenuItem;

public class Pizza extends MenuItem {
    private boolean stuffedCrust;
    private double meatPrice;
    private double cheesePrice;
    private double stuffedCrustPrice;

    public Pizza( String size, String crustType, double basePrice, double meatPrice, double cheesePrice) {
        super("Pizza", size, crustType, basePrice);
        this.stuffedCrust = false;
        this.meatPrice = meatPrice;
        this.stuffedCrustPrice = 2.00;
        this.cheesePrice = cheesePrice;
    }

    //--- Custom pizza options ---
    public void addStuffedCrust(){
        this.stuffedCrust = true;
        applySpecial(stuffedCrustPrice);
    }

    public boolean hasStuffedCrust(){
        return stuffedCrust;
    }

    @Override
    public void calculateTotal(){

    }

    @Override
    public void displayDetails() {
        System.out.println("\n--- PIZZA DETAILS ---");
        System.out.println("Size: " + getSize());
        System.out.println("Crust Type: " + getType());
        System.out.println("Stuffed Crust: " + (stuffedCrust ? "Yes (+$" + stuffedCrustPrice + ")" : "No"));
        System.out.println("Meats: " + (getMeats().isEmpty() ? "None" : String.join(", ", getMeats())));
        System.out.println("Cheeses: " + (getCheeses().isEmpty() ? "None" : String.join(", ", getCheeses())));
        System.out.println("Toppings: " + (getRegularToppings().isEmpty() ? "None" : String.join(", ", getRegularToppings())));
        System.out.println("Sauces: " + (getSauces().isEmpty() ? "None" : String.join(", ", getSauces())));
        System.out.printf("💰 Total Price: $%.2f\n", getTotalPrice());
    }
}
