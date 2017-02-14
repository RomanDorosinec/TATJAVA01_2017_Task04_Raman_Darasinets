package com.epam.task3.dao.manager;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Class provides resources from .resource file
 */
public class DBResourceManager {
    private final static DBResourceManager instance = new DBResourceManager();

    private ResourceBundle bundle = ResourceBundle.getBundle("resource", Locale.ENGLISH);

    /**
     * Singleton implementation
     */
    public static DBResourceManager getInstance() {
        return instance;
    }

    /**
     * Method returns values according to key. got as argument
     *
     * @param key value of key to return string
     * @return String of value by key
     */
    public String getValue(String key) {
        return bundle.getString(key);
    }
}
