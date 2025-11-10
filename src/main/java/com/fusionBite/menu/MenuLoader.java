package com.fusionBite.menu;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Map;

public class MenuLoader {
    private static Map<String,Object> menuData;
    public static void loadMenuData(){
        Gson gson = new Gson();
        try(FileReader reader = new FileReader("src/main/resources/menu.json")) {
            Type type = new TypeToken<ArrayList<MenuItem>>(){}.getType();
            ArrayList<MenuItem> menuItems = gson.fromJson(reader,type);
            System.out.println("✅ Menu Loaded Successfully:");
            menuItems.forEach(System.out::println);
        } catch (IOException e) {
            System.err.println("❌ Error loading menu: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
