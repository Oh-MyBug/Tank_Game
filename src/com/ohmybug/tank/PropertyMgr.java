package com.ohmybug.tank;

import java.io.IOException;
import java.util.Properties;

public class PropertyMgr {
    private static Properties properties;

    static {
        try {
            properties = new Properties();
            properties.load(PropertyMgr.class.getClassLoader().getResourceAsStream("config"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String get(String key){
        return (String)properties.get(key);
    }
}
