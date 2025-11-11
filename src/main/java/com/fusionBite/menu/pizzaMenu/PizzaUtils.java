package com.fusionBite.menu.pizzaMenu;

import com.fusionBite.menu.MenuUI;
import com.fusionBite.utils.Utils;

import java.util.Scanner;

import static com.fusionBite.menu.drinksMenu.DrinkUI.drinkScreen;
import static com.fusionBite.menu.pizzaMenu.PizzaMenu.startOrder;

public class PizzaUtils {
    static Scanner scanner = new Scanner(System.in);
    public static void pizzaMenuScreen(){
        boolean running = true;
        while (running){
            System.out.println("\n🍽️  Fusion Bite — Order Menu");
            System.out.println("--------------------------------");
            System.out.println("1) Add Pizza");
            System.out.println("2) Add Drink");
            System.out.println("3) Add Main Side");
            System.out.println("4) Checkout");
            System.out.println("5) Cancel Order");
            System.out.println("0) Return to Home");
            System.out.println("--------------------------------");
            int choice = Utils.readNumber(scanner,"Enter a number: ", Integer.class);
            switch (choice) {
                case 1 -> startOrder();
                case 2 -> drinkScreen();
                case 3 -> addMainSide();
                case 4 -> checkout();
                case 5 -> cancelOrder();
                case 0 -> {
                    System.out.println("Returning to home...");
                    MenuUI.startApplication();
                    running = false;
                }
                default -> System.out.println("⚠️ Invalid input. Please enter a number between 0–5.");
            }
        }
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

}
