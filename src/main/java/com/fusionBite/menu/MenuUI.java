package com.fusionBite.menu;

import com.fusionBite.menu.pizzaMenu.PizzaMenu;
import java.util.Scanner;

public class MenuUI {
    static Scanner scanner = new Scanner(System.in);


    public static void menu(){
        System.out.println("\tMenu");
        System.out.println("1. Pizza");
        System.out.println("2. Sandwich");
        System.out.println("3. Taco");
        System.out.println("4. Exit");

        boolean running = true;
        while(running){
            System.out.print("Enter your choice:");
            int choice = scanner.nextInt();
            switch (choice){
                case 1 -> PizzaMenu.startOrder();
                case 4 -> {
                    System.out.println("Exiting menu...");
                    running = false;
                }
                default -> System.out.println("Invalid choice, try again.");
            }
        }
    }
}
