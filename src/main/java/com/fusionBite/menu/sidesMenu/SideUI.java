package com.fusionBite.menu.sidesMenu;

import com.fusionBite.menu.MenuLoader;
import com.fusionBite.menu.order.Order;
import com.fusionBite.utils.Utils;

import java.util.Map;
import java.util.Scanner;

public class SideUI {
    static final Scanner scanner = new Scanner(System.in);

    public static void sideMenu(){
        MenuLoader.loadMenuData();
        Map<String, Object> sideMenu = MenuLoader.getSection("Side Items");

        if (sideMenu == null) {
            System.out.println("⚠️ Unable to load Side Items from menu.json");
            return;
        }

        System.out.println("\n🍟 Available Sides:");
        System.out.println("--------------------------");
        for (Map.Entry<String, Object> entry : sideMenu.entrySet()) {
            String item = entry.getKey();
            double price = ((Number) entry.getValue()).doubleValue();
            System.out.printf("- %-20s : $%.2f%n", item, price);
        }
        System.out.println("--------------------------");

        int sideCount = Utils.readNumber(scanner, "How many sides would you like to add? (0 for none): ", Integer.class);

        for (int i = 1; i <= sideCount; i++) {
            System.out.printf("%n🍟 Side #%d:%n", i);

            System.out.print("Enter side name exactly (e.g., Chips, Garlic Knots): ");
            String chosenSide = scanner.nextLine().trim().toLowerCase();

            String matchedSide = sideMenu.keySet().stream()
                    .filter(key->key.equalsIgnoreCase(chosenSide))
                    .findFirst()
                    .orElse(null);


            if (matchedSide==null) {
                System.out.println("⚠️ Invalid side — skipping.");
                continue;
            }

            double price = ((Number) sideMenu.get(matchedSide)).doubleValue();

            Side side = new Side(matchedSide, price);
            Order.addItem(side);

            System.out.printf("✅ %s added — $%.2f%n", matchedSide, price);
        }
        System.out.println("\n🧾 Current Sides in Order:");
        Order.displayOrderSummary();
    }
}
