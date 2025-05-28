package com.Lexico.FlexLexico.store;

import java.util.HashMap;
import java.util.Map;

public class StoreMessages {
    
    private static Map<String, String> store = new HashMap<String,String>();

    private StoreMessages() {
        // Private constructor to prevent instantiation
    }

    public static void setMessage(String key, String message) {
        StoreMessages.store.put(key, message);
    }

    public static String getKeyByValue(String value) {
        // remove first and last quotes
        String valueToFind = value.replaceAll("^\"|\"$", "'");
        for (Map.Entry<String, String> entry : StoreMessages.store.entrySet()) {
            if (entry.getValue().equals(valueToFind)) {
                return entry.getKey();
            }
        }
        return null;
    }

    public static void clear() {
        StoreMessages.store.clear();
    }
}
