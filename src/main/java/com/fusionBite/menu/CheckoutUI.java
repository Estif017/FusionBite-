package com.fusionBite.menu;

import com.fusionBite.menu.order.Order;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class CheckoutUI {
    static Scanner scanner = new Scanner(System.in);

    public static void checkoutScreen(){
        if (Order.isOrderEmpty()) {
            System.out.println("⚠️ No items in the order. Please add items before checkout.");
            return;
        }
        System.out.println("\n===============================");
        System.out.println("🧾 FINAL ORDER SUMMARY");
        System.out.println("===============================");
        Order.displayOrderSummary();

        System.out.println("\nConfirm order? (yes/no): ");
        String confirm = scanner.nextLine().trim().toLowerCase();

        if(confirm.equalsIgnoreCase("yes")||confirm.equalsIgnoreCase("y")){
            saveReceipt();
            Order.clearOrder();
            System.out.println("🎉 Order completed! Returning to Home screen....");
            MenuUI.startApplication();
        }else{
            System.out.println("❌ Order canceled. Returning to Home screen...");
            Order.clearOrder();
            MenuUI.startApplication();
        }
        scanner.close();
    }

    private  static  void saveReceipt(){
        try{
            File folder = new File("receipts");
            if(!folder.exists()) folder.mkdir();

            String fileName = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss"))+".txt";
            FileWriter writer = new FileWriter("receipts/"+fileName);

            writer.write("🧾 FusionBite Receipt\n");
            writer.write("Date "+LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))+"\n");
            writer.write("=====================================\n\n");

            writer.write(Order.generateReceiptText());
            writer.write("\n=====================================\n");
            writer.write(String.format("TOTAL AMOUNT: $%.2f%n", Order.calculateTotal()));
            writer.write("=====================================\n");
            writer.close();

            System.out.println("\n📄 Receipt saved as: receipts/" + fileName);
        } catch (IOException e) {
            System.out.println("⚠️ Error saving receipt: " + e.getMessage());
        }
    }
}
