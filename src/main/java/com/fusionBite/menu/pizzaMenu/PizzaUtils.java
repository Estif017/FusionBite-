package com.fusionBite.menu.pizzaMenu;

import com.fusionBite.menu.MenuUI;
import com.fusionBite.menu.order.Order;
import com.fusionBite.utils.Utils;

import java.util.Scanner;

import static com.fusionBite.menu.drinksMenu.DrinkUI.drinkScreen;
import static com.fusionBite.menu.order.Order.clearOrder;
import static com.fusionBite.menu.pizzaMenu.PizzaMenu.startOrder;

public class PizzaUtils {
    static Scanner scanner = new Scanner(System.in);
    private static double lastPizzaBatchTotal = 0;
    public static void pizzaMenuScreen(){
        displayOrderBanner();
        boolean running = true;
        while (running){
            System.out.println("\n🍽️  Fusion Bite — Order Menu");
            System.out.println("--------------------------------");
            System.out.println("1) Add Pizza");
            drinkAndSide();
            checkOutAndCancel();
            System.out.println("0) Return to Home");
            System.out.println("--------------------------------");
            int choice = Utils.readNumber(scanner,"Enter a number: ", Integer.class);
            switch (choice) {
                case 1 -> startOrder();
                case 2 -> drinkScreen();
                case 3 -> addMainSide();
                case 4 -> checkout();
                case 5 -> {
                    cancelOrder();
                    clearOrder();
                    System.out.println("❌ Order canceled.");
                }
                case 0 -> {
                    System.out.println("Returning to home...");
                    MenuUI.startApplication();
                    running = false;
                }
                default -> System.out.println("⚠️ Invalid input. Please enter a number between 0–5.");
            }
        }
    }

    public static void drinkAndSide() {
        System.out.println("2) Add Drink");
        System.out.println("3) Add Main Side");
    }
    public static void checkOutAndCancel(){
        System.out.println("4) Checkout");
        System.out.println("5) Cancel Order");
    }

    public static void addDrink(){
        System.out.println("Adding drink");
    }
    public static void addMainSide(){
        System.out.println("Adding main side");
    }
    public static void checkout(){
        System.out.println("Checking out...");
    }public static void cancelOrder(){
        System.out.println("Canceling order...");
    }

    public static void updateLastPizzaBatchTotal(double total){
        lastPizzaBatchTotal = total;
    }

    public static void displayOrderBanner(){
        if (Order.getPizzaCount() == 0) {
            lastPizzaBatchTotal = 0;
        }

        if (Order.getPizzaCount() > 0) {
            System.out.printf("#%d%n", Order.getPizzaCount());
        }

        if (lastPizzaBatchTotal > 0) {
            System.out.printf("💰 Total Price: $%.2f%n", lastPizzaBatchTotal);
        }

        double aggregateTotal = Order.calculateTotal();
        if (aggregateTotal > 0) {
            System.out.printf("Total Order Price: $%.2f%n", aggregateTotal);
            System.out.println("would you like to add drink and sides?");

        }
    }
}
