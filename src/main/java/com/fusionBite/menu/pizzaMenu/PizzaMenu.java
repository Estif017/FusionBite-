package com.fusionBite.menu.pizzaMenu;

import com.fusionBite.menu.MenuItem;
import com.fusionBite.menu.MenuLoader;
import com.fusionBite.menu.MenuUI;
import com.fusionBite.utils.MenuHelper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.*;

//import static com.fusionBite.menu.MenuLoader.menuData;

public class PizzaMenu {
    private static Scanner scanner = new Scanner(System.in);
    public static Pizza buildPizzaOrder(){
        MenuLoader.loadMenuData();
        Map<String,Object> pizzaMenu = MenuLoader.getSection("Pizza Menu");
        if(pizzaMenu==null) {
            System.out.println("⚠️ Could not load Pizza Menu.");
            return null;
        }

        System.out.println("🍕 Pizza Menu:");
        System.out.println("-----------------------------");

        Map<String, Object> orderDetails = new LinkedHashMap<>();
        double totalPrice=0;

        //--- size ---
        String chosenSize = MenuHelper.chooseSize(pizzaMenu,orderDetails);
        Map<String,Object> sizes =  (Map<String, Object>) pizzaMenu.get("sizes");
        Map<String,Object> sizeInfo = (Map<String, Object>) sizes.get(chosenSize);

        double basePrice =((Number)sizeInfo.get("basePrice")).doubleValue();
        double meatPrice = ((Number)sizeInfo.get("meatPrice")).doubleValue();
        double cheesePrice = ((Number)sizeInfo.get("cheesePrice")).doubleValue();

       //-- Choose Crust ---
        List<String> crustOptions = (List<String>) pizzaMenu.get("crust");
        System.out.println("\nAvailable Crusts: " + String.join(", ", crustOptions));
        System.out.print("Choose crust: ");
        String crustChoice = scanner.nextLine().trim().toLowerCase();
        if(!crustOptions.contains(crustChoice)){
            System.out.println("⚠️ Invalid crust — defaulting to regular.");
            crustChoice = "regular";
        }

        Pizza pizza = new Pizza(chosenSize,crustChoice,basePrice,meatPrice,cheesePrice);

        //-- stuffed Crust option
        System.out.print("Would you like stuffed crust (+$2.00)? (yes/no): ");
        if (scanner.nextLine().trim().equalsIgnoreCase("yes")) {
            pizza.addStuffedCrust();
        }

        //-- Add Meats ---
        List<String> meats = (List<String>) pizzaMenu.get("meats");
        List<String> chosenMeats = MenuHelper.chooseItems("meats",meats);
        for(String m: chosenMeats) pizza.addMeats(m,meatPrice);

        //--Add Cheeses
        List<String> cheeses = (List<String>) pizzaMenu.get("cheeses");
        List<String> chosenCheeses = MenuHelper.chooseItems("cheeses",cheeses);
        for(String c: chosenCheeses) pizza.addCheese(c,cheesePrice);

        //----Add Toppings ---
        List<String> toppings = (List<String>) pizzaMenu.get("regularToppings");
        List<String> chosenToppings = MenuHelper.chooseItems( "toppings", toppings);
        for (String t : chosenToppings) pizza.addRegularTopping(t);

        // --- Add Sauces ---
        List<String> sauces = (List<String>) pizzaMenu.get("sauces");
        List<String> chosenSauces = MenuHelper.chooseItems( "sauces", sauces);
        for (String s : chosenSauces) pizza.addSauce(s);

        return pizza;
    }


    public static void startOrder(){
        Scanner scanner = new Scanner(System.in);
        List<Map<String,Object>>orderList = new ArrayList<>();
        double orderTotal = 0;

        System.out.print("How many pizzas would you like to order? (0 or more): ");
        int pizzaCount = scanner.nextInt();
        scanner.nextLine();

        if(pizzaCount==0){
            System.out.println("You must order garlic knots or a drink if you order no pizzas.");
            System.out.print("Would you like garlic knots ($4.00) or a drink ($2.50)? ");
            String item = scanner.nextLine().trim().toLowerCase();
            if(item.contains("garlic")){
                orderTotal+=4.00;
                System.out.println("Added garlic knots to your order.");
            } else if (item.contains("drink")) {
                orderTotal+=2.50;
                System.out.println("Added drink to your order.");
            }else{
                System.out.println("Invalid choice — no order placed.");
                return;
            }
            System.out.printf("Order total: $%.2f\n", orderTotal);
            return;
        }
        //If customer orders pizzas
        List<Pizza> pizzas = new ArrayList<>();
        double totalOrder = 0;
        for(int i=1;i<=pizzaCount;i++){
            System.out.println("\n--- Pizza #" + i + " ---");
            Pizza pizza = buildPizzaOrder();
            pizzas.add(pizza);
            totalOrder+=pizza.getTotalPrice();
        }

        System.out.println("\"\\n=== ORDER SUMMARY ===\"");
        System.out.println("-----------------------------");
        for(int i = 0; i<pizzas.size();i++){
            System.out.println("🍕 Pizza #" + (i + 1));
            pizzas.get(i).displayDetails();
            System.out.println("-----------------------------");
        }
        System.out.printf("Total Order Price: $%.2f\n", totalOrder);
        MenuUI.menu();
    }
}
