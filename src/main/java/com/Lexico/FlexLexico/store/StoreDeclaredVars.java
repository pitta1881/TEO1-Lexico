package com.Lexico.FlexLexico.store;

import java.util.HashMap;
import java.util.Map;

import com.Lexico.FlexLexico.TokenObject;

public class StoreDeclaredVars {
    private static Map<String, TokenObject> store = new HashMap<>();

    private StoreDeclaredVars() {
        // Private constructor to prevent instantiation
    }

    public static void put(String key, TokenObject value) {
        StoreDeclaredVars.store.put(key, value);
    }

    public static boolean containsKey(String key) {
        return StoreDeclaredVars.store.containsKey(key);
    }

    public static TokenObject get(String key) {
        return StoreDeclaredVars.store.get(key);
    }

    public static void clear() {
        StoreDeclaredVars.store.clear();
    }
}
