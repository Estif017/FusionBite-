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
    private Scanner scanner = new Scanner(System.in);


    public static void loadPizzaMenu(){
        MenuLoader.loadMenuData();
        Map<String,Object> pizzaMenu = MenuLoader.getSection("Pizza Menu");
        if(pizzaMenu==null) return;

        System.out.println("🍕 Pizza Menu:");
        System.out.println("-----------------------------");

        Map<String, Object> order = new HashMap<>();
        double totalPrice=0.0;

        //--- size ---
        String chosenSize = MenuHelper.chooseSize(pizzaMenu,order);
        Map<String,Object> sizes =  (Map<String, Object>) pizzaMenu.get("sizes");
        Map<String,Object> sizeInfo = (Map<String, Object>) sizes.get(chosenSize);
        totalPrice+=((Number)sizeInfo.get("basePrice")).doubleValue();
        System.out.println(order);
        System.out.println(totalPrice);
    }




    public static Map<String,Object> pizzaMenuDisplay(Scanner scanner){
        Map<String,Object> orderDetails = new HashMap<>();
        double price = 0;
        StringBuilder summary = new StringBuilder("Your Choice\n");

        //--- Size Selection ----
//        System.out.println("Sizes:");
//        System.out.println("8\"  - $8.50");
//        System.out.println("12\" - $12.00");
//        System.out.println("16\" - $16.50");
//        System.out.print("Choose your size (8, 12, 16): ");

        String sizeChoice = scanner.nextLine().trim();
        String size = "";
        switch (sizeChoice){
            case "8"->{size = "8\"";price+=8.50;}
            case "12" -> { size = "12\""; price += 12.00; }
            case "16" -> { size = "16\""; price += 16.50; }
            default -> { System.out.println("Invalid size. Defaulting to 12\"."); size = "12\""; price += 12.00; }
        }
        orderDetails.put("size",size);
        summary.append("Size:").append(size).append("\n");
        System.out.println(summary);

        // --- Crust selection ---
        System.out.println("\nAvailable Crusts: thin, regular, thick, cauliflower");
        System.out.print("Choose your crust: ");
        String crustChoice = scanner.nextLine().trim().toLowerCase();

        List<String>  validCrusts = List.of("thin","regular","thick","cauliflower");
        String crust = validCrusts.contains(crustChoice) ? crustChoice:"regular";
        orderDetails.put("crust",crust);
        summary.append("Crust: ").append(crust).append("\n");

        //---Stuffed Crust ---
        System.out.print("Would you like stuffed crust? (+$2.00) (yes/no): ");
        String stuffed = scanner.nextLine().trim().toLowerCase();
        boolean hasStuffCrust = stuffed.equals("yes");
        if(hasStuffCrust) price+=2.00;
        orderDetails.put("stuffedCrust",hasStuffCrust?"Yes":"No");

        //---Adjusting pricing for size----
        double extraMeatPrice,extraCheesePrice;
        switch (size){
            case "8\"" -> { extraMeatPrice = 1.00; extraCheesePrice = 0.75; }
            case "12\"" -> { extraMeatPrice = 2.00; extraCheesePrice = 1.50; }
            default -> { extraMeatPrice = 2.25; extraCheesePrice = 2.25; }
        }
        //--- Meats ---
        String[] meats =  {"pepperoni", "sausage", "ham", "bacon", "chicken", "meatball"};
        List<String> selectedMeats = getUserSelections(scanner,meats,"meats");
        price+=calculateExtraCoast(selectedMeats.size(),extraMeatPrice);
        orderDetails.put("meats",selectedMeats);
        summary.append("Meats: ").append(selectedMeats).append("\n").append("Price: "+price).append("\n");
        System.out.println(summary);

        //--- Cheese ---
        String[] cheese = { "mozzarella", "parmesan", "ricotta", "goat cheese", "buffalo" };
        List<String> selectedCheese = getUserSelections(scanner,cheese,"cheese");
        price+=calculateExtraCoast(selectedCheese.size(),extraCheesePrice);
        orderDetails.put("cheese",selectedCheese);
        summary.append("Cheese: ").append(selectedCheese).append("\n").append("Price: "+price).append("\n");
        System.out.println(summary);

        // --- Regular Toppings ---
        String[] toppings = { "onions", "mushrooms", "bell peppers", "olives", "tomatoes", "spinach", "basil", "pineapple", "anchovies" };
        List<String> selectedToppings = getUserSelections(scanner, toppings, "toppings");
        orderDetails.put("toppings", selectedToppings);
        summary.append("Toppings: ").append(selectedToppings).append("\n");
        System.out.println(summary);

        // --- Sauces ---
        String[] sauces = { "marinara", "alfredo", "pesto", "bbq", "buffalo", "olive oil" };
        List<String> selectedSauces = getUserSelections(scanner, sauces, "sauces");
        orderDetails.put("sauces", selectedSauces);
        summary.append("Sauces: ").append(selectedSauces).append("\n");

        //--- Ask if the user wants a drink ----
        System.out.println("Would you like to add a drink?");
        String drink = scanner.nextLine().toLowerCase().trim();
        boolean isThereAdrink = drink.equals("yes");
        if(isThereAdrink){
            orderDetails.put("Drink","yes");
            price+=2.50;
        }

        // --- Final Summary ---
        orderDetails.put("price", price);
        summary.append("Total Price: $").append(String.format("%.2f", price)).append("\n");

        System.out.println("\n=== ORDER SUMMARY ===");
        System.out.println(summary);

        return orderDetails;
    }

    public static List<String> getUserSelections(Scanner scanner,String[] options,String label){
        System.out.println("\nAvailable " + label + ": " + String.join(", ", options));
        System.out.print("Enter your " + label + " (separate by commas, or leave blank for none): ");
        String input = scanner.nextLine().trim().toLowerCase();

        List<String> validOptions = List.of(options);
        List<String> chosenItems = new ArrayList<>();

        if(!input.isEmpty()){
            String[] parts = input.split(",");
            for(String p : parts){
                String item = p.trim();
                if(validOptions.contains(item)){
                    chosenItems.add(item);
                }else{
                    System.out.println("Ignoring invalid " + label + ": " + item);
                }
            }
        }
        return chosenItems;
    }

    public static double calculateExtraCoast(int itemCount,double extraprice){
        if(itemCount<=1) return 0;
        return (itemCount-1)*extraprice;
    }


    public static void startOrder(){
        Scanner scanner = new Scanner(System.in);
        List<Map<String,Object>> orderList = new ArrayList<>();
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
        for(int i=1;i<=pizzaCount;i++){
            System.out.println("\n--- Pizza #" + i + " ---");
//            Map<String, Object> pizza = pizzaMenuDisplay(scanner);
//            orderList.add(pizza);
//            orderTotal+=(double) pizza.get("price");
            loadPizzaMenu();
        }

        System.out.println("\"\\n=== ORDER SUMMARY ===\"");
        for(int i = 0; i<orderList.size();i++){
            System.out.println("Pizza #" + (i + 1) + ": " + orderList.get(i));
        }
        System.out.printf("Total Order Price: $%.2f\n", orderTotal);
        MenuUI.menu();
    }
}
