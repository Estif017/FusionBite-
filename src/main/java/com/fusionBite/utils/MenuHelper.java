package com.fusionBite.utils;

import java.util.Map;
import java.util.Scanner;

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
}
