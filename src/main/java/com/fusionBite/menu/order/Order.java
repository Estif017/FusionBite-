package com.fusionBite.menu.order;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private static final List<Orderable> items = new ArrayList<>();

    public static void addItem(Orderable item){
        items.add(item);
    }

    public static void displayOrderSummary(){
        if (items.isEmpty()) {
            System.out.println("🛒 No items in your order yet.");
            return;
        }
        System.out.println("\n🧾 ORDER SUMMARY");
        System.out.println("---------------------------------");
        for (Orderable item : items) {
            System.out.println(item.getDescription());
        }
        System.out.printf("\nTOTAL: $%.2f%n", calculateTotal());
    }

    public static double calculateTotal(){
        return items.stream().mapToDouble(Orderable::getPrice).sum();
    }

    public static void clearOrder(){
        items.clear();
    }
    public static int getItemCount() {
        return items.size();
    }

    public static boolean isOrderEmpty() {
        return items.isEmpty();
    }

    public static String generateReceiptText() {
        StringBuilder sb = new StringBuilder();
        for (Orderable item : items) sb.append(item.getDescription()).append("\n");
        return sb.toString();
    }
}
