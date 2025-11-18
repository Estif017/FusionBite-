package com.fusionBite.menu.pizzaMenu;

import com.fusionBite.menu.MenuItem;
import com.fusionBite.menu.order.Orderable;

public class Pizza extends MenuItem implements Orderable {
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
    public void calculateTotal() {
        double total = getBasePrice();

        // Premium meats (size-based cost) - only charge *after first one*
        if (getMeats() != null && !getMeats().isEmpty()) {
            int extraMeatCount = Math.max(0, getMeats().size() - 1);
            total += extraMeatCount * meatPrice;
        }

        // Premium cheeses (size-based cost) - only charge *after first one*
        if (getCheeses() != null && !getCheeses().isEmpty()) {
            int extraCheeseCount = Math.max(0, getCheeses().size() - 1);
            total += extraCheeseCount * cheesePrice;
        }

        // Stuffed crust option
        if (stuffedCrust) {
            total += stuffedCrustPrice;
        }

        setTotalPrice(total);
    }

    @Override
    public double getPrice(){return getTotalPrice();}

    @Override
    public String getDescription(){
        return String.format("%s %s Pizza with %s, %s | $%.2f",
                getSize(), getType(), String.join(", ", getMeats()),
                String.join(", ", getCheeses()), getTotalPrice());
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
