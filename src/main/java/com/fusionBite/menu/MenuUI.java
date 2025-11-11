package com.fusionBite.menu;

import com.fusionBite.menu.pizzaMenu.PizzaMenu;
import java.util.Scanner;

import static com.fusionBite.menu.drinksMenu.DrinkUI.drinkScreen;
import static com.fusionBite.menu.pizzaMenu.PizzaUtils.pizzaMenuScreen;
import static com.fusionBite.utils.Utils.readNumber;

public class MenuUI {
    static Scanner scanner = new Scanner(System.in);

    public static void startApplication(){
        System.out.println("Welcome to Fusion Bite!!!");
        System.out.println("1) Start a new order");
        System.out.println("0) Exit");
        boolean running = true;
        while (running){
            int choice = readNumber(scanner,"Enter a number",Integer.class);
            if(choice==1){
                menu();
            } else if (choice==0) {
                System.out.println("Thank you for you visit, Have a good day!!!");
                System.exit(0);
            }else{
                System.out.println("Invalid Input. try again (Enter 1 or 0)");
            }
        }

    }

    public static void menu(){

        boolean running = true;
        while(running){
            System.out.println("\tMenu");
            System.out.println("1. Pizza");
            System.out.println("2. Sandwich");
            System.out.println("3. Taco");
            System.out.println("4. Drink");
            System.out.println("0. Exit");
            System.out.print("Enter your choice:");
            int choice = scanner.nextInt();
            switch (choice){
                case 1 -> pizzaMenuScreen();
                case 4->drinkScreen();
                case 0 -> {
                    System.out.println("Exiting menu...");
                    running = false;
                }
                default -> System.out.println("Invalid choice, try again.");
            }
        }
    }
}
