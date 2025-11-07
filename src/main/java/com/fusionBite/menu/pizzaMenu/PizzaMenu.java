package com.fusionBite.menu.pizzaMenu;

import com.fusionBite.menu.MenuItem;

import java.util.*;

public class PizzaMenu extends MenuItem {
    public PizzaMenu(String size, String type, String[] meets, String[] cheese, String[] regularToppings, String[] sauces ) {
        super(size, type, meets, cheese, regularToppings, sauces);
    }

    public static void pizzaMenuDisplay(){
        Scanner scanner = new Scanner(System.in);
        Map<String,Object> orderDetail = new HashMap<>();
        double price = 0;
        StringBuilder summary = new StringBuilder("Your Choice\n");

        //--- Size Selection ----
        System.out.println("Sizes:");
        System.out.println("8\"  - $8.50");
        System.out.println("12\" - $12.00");
        System.out.println("16\" - $16.50");
        System.out.print("Choose your size (8, 12, 16): ");

        String sizeChoice = scanner.nextLine().trim();
        String size = "";
        switch (sizeChoice){
            case "8"->{size = "8\"";price+=8.50;}
            case "12" -> { size = "12\""; price += 12.00; }
            case "16" -> { size = "16\""; price += 16.50; }
            default -> { System.out.println("Invalid size. Defaulting to 12\"."); size = "12\""; price += 12.00; }
        }
        orderDetail.put("size",size);
        summary.append("Size:").append(size).append("\n");
        System.out.println(summary);

        // --- Crust selection ---
        System.out.println("\nAvailable Crusts: thin, regular, thick, cauliflower");
        System.out.print("Choose your crust: ");
        String crustChoice = scanner.nextLine().trim().toLowerCase();

        List<String>  validCrusts = List.of("thin","regular","thick","cauliflower");
        String crust = validCrusts.contains(crustChoice) ? crustChoice:"regular";
        orderDetail.put("crust",crust);
        summary.append("Crust: ").append(crust).append("\n");
        System.out.println(summary);

        //---Adjusting pricing for size----
        double extraMeatPrice =0;
        double extraCheesePrice = 0;
        switch (size){
            case "8\"" -> { extraMeatPrice = 1.00; extraCheesePrice = 0.75; }
            case "12\"" -> { extraMeatPrice = 2.00; extraCheesePrice = 1.50; }
            default -> { extraMeatPrice = 2.25; extraCheesePrice = 2.25; }
        }
        //--- Meats ---
        String[] meats =  {"pepperoni", "sausage", "ham", "bacon", "chicken", "meatball"};
        List<String> selectedMeats = getUserSelections(scanner,meats,"meats");
        price+=calculateExtraCoast(selectedMeats.size(),extraMeatPrice);
        orderDetail.put("meats",selectedMeats);
        summary.append("Meats: ").append(selectedMeats).append("\n");
        System.out.println(summary);

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
}
