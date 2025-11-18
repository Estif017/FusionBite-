package com.fusionBite.menu.drinksMenu;

import com.fusionBite.menu.MenuLoader;
import com.fusionBite.menu.order.Order;
import com.fusionBite.utils.Utils;

import java.util.*;

import static com.fusionBite.menu.order.Order.getTotalDrinkPrice;


public class DrinkUI {
    static Scanner scanner = new Scanner(System.in);
    public static void drinkScreen(){
        MenuLoader.loadMenuData();
        Map<String,Object> drinkMenu = MenuLoader.getSection("Drinks");
        Drink drink = null;

        if (drinkMenu == null) System.out.println("⚠️ Unable to load Drinks section from menu.json");

        System.out.println("\n🥤 Available Drinks:");
        System.out.println("--------------------------");
        for (Map.Entry<String, Object> entry : drinkMenu.entrySet()) {
            String size = capitalize(entry.getKey());
            double price = ((Number) entry.getValue()).doubleValue();
            System.out.printf("%-8s - $%.2f%n", size, price);
        }
        System.out.println("--------------------------");
        System.out.println("How many drinks you would like to order?");
        int drinkscount = Utils.readNumber(scanner,"Enter the Number of drinks you would like to order", Integer.class);
        List<String> validSizes = List.of("small", "medium", "large");
        for(int i = 1; i<=drinkscount;i++){
            System.out.printf("%n🥤 Drink #%d:%n", i);

//              choose size
            System.out.print("Enter drink size (Small / Medium / Large): ");
            String chosenSize = scanner.nextLine().trim().toLowerCase();
            if (!validSizes.contains(chosenSize)) {
                System.out.println("⚠️ Invalid selection — defaulting to small drink.");
                chosenSize = "small";
            }

            double chosenPrice = ((Number) drinkMenu.get(chosenSize)).doubleValue();

            System.out.print("Enter drink flavor (e.g., Coke, Sprite, Lemonade): ");
            String flavor = scanner.nextLine().trim();
            if (flavor.isEmpty()) flavor = "Regular";

             drink = new Drink(capitalize(chosenSize),capitalize(flavor),chosenPrice);
            Order.addDrink(drink);

            System.out.println("\n✅ Drink added:");
            System.out.printf("Size: %s | Flavor: %s | Price: $%.2f%n", drink.getSize(), drink.getFlavor(), drink.getPrice());
        }
        System.out.println("\n🧾 Current Drinks in Order:");
        Order.displayDrinks();
        getTotalDrinkPrice();
        System.out.println("Total Orders : "+Order.calculateTotal());
    }

    private static String capitalize(String s) {
        if (s == null || s.isEmpty()) return s;
        return s.substring(0, 1).toUpperCase() + s.substring(1);
    }
}
