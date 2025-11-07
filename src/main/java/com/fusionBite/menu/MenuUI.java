package com.fusionBite.menu;

import com.fusionBite.menu.pizzaMenu.PizzaMenu;

import java.util.Scanner;

public class MenuUI {
    static Scanner scanner = new Scanner(System.in);
    public static void menu(){
        System.out.println("\tmenu\n1.pizza\n2.sandwich\n3.taco\n4.taco");
        System.out.print("Enter your choice:");
        boolean running = true;
        while(running){
            int choice = scanner.nextInt();
            switch (choice){
                case 1 -> PizzaMenu.pizzaMenuDisplay();
            }
        }
    }
}
