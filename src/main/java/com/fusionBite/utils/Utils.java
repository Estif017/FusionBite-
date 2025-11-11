package com.fusionBite.utils;

import java.util.Scanner;

public class Utils {
    public static <T extends Number> T readNumber(Scanner scanner1 , String message, Class<T> type){
        //keeps prompting until a valid input
        while (true){
            System.out.print(message);
            String input = scanner1.nextLine();
            try{
                if(type==Integer.class){
                    return (T) Integer.valueOf(Integer.parseInt(input));
                } else if (type==Double.class) {
                    return (T) Double.valueOf(Double.parseDouble(input));
                }else {
                    System.out.println("⚠️ Unsupported number type: " + type.getSimpleName());
                    return null;
                }
            }catch (NumberFormatException e) {
                System.out.println("❌ Invalid input! Please enter a valid " + type.getSimpleName() + ".");
            }
        }
    }
}
