package com.fusionBite.menu.pizzaMenu;

import com.fusionBite.menu.MenuUI;
import com.fusionBite.menu.order.Order;
import com.fusionBite.utils.Utils;

import java.util.Scanner;

import static com.fusionBite.menu.drinksMenu.DrinkUI.drinkScreen;
import static com.fusionBite.menu.order.Order.clearOrder;
import static com.fusionBite.menu.pizzaMenu.PizzaMenu.startOrder;
import static com.fusionBite.menu.sidesMenu.SideUI.sideMenu;

public class PizzaUtils {
    static Scanner scanner = new Scanner(System.in);
    public static void pizzaMenuScreen(){
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
                case 3 -> sideMenu();
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

    public static void checkout(){
        System.out.println("Checking out...");
    }public static void cancelOrder(){
        System.out.println("Canceling order...");
    }


}
