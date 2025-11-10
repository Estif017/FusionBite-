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
            Type type = new TypeToken<Map<String,Object>>() {}.getType();
            menuData = gson.fromJson(reader,type);
            System.out.println("✅ Menu Loaded Successfully:");
            System.out.println(menuData.keySet());
        } catch (IOException e) {
            System.err.println("❌ Error loading menu: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static Map<String,Object> getMenuData(){
        return menuData;
    }

    public static Map<String,Object> getSection(String sectionName){
        if(menuData == null){
            System.err.println("⚠️ Menu data not loaded. Call loadMenuData() first.");
            return null;
        }
        return (Map<String, Object>) menuData.get(sectionName);
    }
}
