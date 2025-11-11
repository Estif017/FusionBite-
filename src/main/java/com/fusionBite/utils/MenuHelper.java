package com.fusionBite.utils;

import java.util.*;

public class MenuHelper {
    private static Scanner scanner = new Scanner(System.in);

    public static String chooseSize(Map<String,Object> menuSection, Map<String,Object> orderDetails){
        Map<String,Object> sizes = (Map<String, Object>) menuSection.get("sizes");
        System.out.println("\n Choose Size: ");
        for(String sizeKey: sizes.keySet()){
            Map<String,Object> sizeInfo = (Map<String, Object>) sizes.get(sizeKey);
            double basePrice = ((Number) sizeInfo.get("basePrice")).doubleValue();
            System.out.printf("  %-6s - $%.2f%n", sizeKey, basePrice);
        }
        System.out.println("Enter size: ");
        String choice = scanner.nextLine().trim();
        if (!choice.endsWith("\"")) {
            choice = choice + "\""; // automatically add the missing quote
        }

        if(!sizes.containsKey(choice)){
            System.out.println("⚠️ Invalid size — defaulting to first option.");
            choice = sizes.keySet().iterator().next();//gets the first key from maps key set
        }
        Map<String,Object> chosen = (Map<String, Object>) sizes.get(choice);
        double basePrice = ((Number)chosen.get("basePrice")).doubleValue();
        orderDetails.put("Size",choice);
        orderDetails.put("Base Price", basePrice);
        return choice;
    }

    public static List<String> chooseItems(String label, List<String> availableItems){
        System.out.println("\nSelect " + label + " (comma-separated):");
        System.out.println("  " + String.join(", ", availableItems));
        System.out.print("Your choices: ");

        String input = scanner.nextLine().trim().toLowerCase();
        List<String> chosen = new ArrayList<>();
        if(!input.isEmpty()){
            for(String part : input.split(",")){
                String item = part.trim();
                if(availableItems.contains(item)) chosen.add(item);
                else System.out.println("Ignoring invalid " + label + ": " + item);
            }
        }
        return chosen;
    }

    public static double calculateExtraCost(int count,double perItemCost){
        if (count <= 1) return 0;
        return (count - 1) * perItemCost;
    }

    public static void displayProgress(Map<String,Object> details, double price){
        System.out.println("\n🧾 Current Order Status:");
        System.out.println("-----------------------------");
        for(Map.Entry<String,Object> entry : details.entrySet()){
            System.out.printf("%-15s: %s%n", entry.getKey(), entry.getValue());
        }
        System.out.printf("💰 Current Price: $%.2f%n", price);
        System.out.println("-----------------------------");
    }
}
