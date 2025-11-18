package com.fusionBite.menu.order;

import com.fusionBite.menu.drinksMenu.Drink;
import com.fusionBite.menu.pizzaMenu.Pizza;
import com.fusionBite.menu.sidesMenu.Side;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private static List<Pizza> pizzas = new ArrayList<>();
    private static List<Drink> drinks = new ArrayList<>();
    private static List<Side> sides = new ArrayList<>();

    public static double calculateTotal(){
        double total = 0;
        for(Pizza pizza: pizzas) total+=pizza.getTotalPrice();
        for (Drink drink: drinks) total+=drink.getPrice();
        for (Side side:sides) total+=side.getPrice();
        return total;
    }

    public static void addPizza(Pizza pizza){
        pizzas.add(pizza);
    }

    public static void addDrink(Drink drink) {
        drinks.add(drink);
    }

    public static void addSides(Side side){
        sides.add(side);
    }

    public static void getTotalDrinkPrice(){
        if (!drinks.isEmpty()) {
            System.out.println("\n🥤 Drinks:");
            double totalPrice = 0;
            for (Drink d : drinks){
                totalPrice+=d.getPrice();
            }
            System.out.println("- $" +totalPrice);
        }
    }


    public static void displayDrinks(){
        if(drinks.isEmpty()) System.out.println("No drinks added yet.");
        for(int i = 0; i<drinks.size();i++){
            System.out.println((i + 1) + ". " + drinks.get(i).getSize()+" "+drinks.get(i).getFlavor()+" "+drinks.get(i).getPrice());
        }
    }

    public static int getPizzaCount(){
        return pizzas.size();
    }

    public static int getDrinkCount(){
        return pizzas.size();
    }

    public static int getSideCount(){
        return pizzas.size();
    }

    public static void clearOrder() {
        pizzas.clear();
        drinks.clear();
        sides.clear();
        System.out.println("🗑️ Order cleared.");
    }
}
