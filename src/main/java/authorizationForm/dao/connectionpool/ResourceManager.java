package main.java.authorizationForm.dao.connectionpool;

import java.util.ResourceBundle;

public class ResourceManager {

    private static ResourceManager instance;
    private ResourceBundle bundle = ResourceBundle.getBundle("database");

    private ResourceManager() {
    }

    public static ResourceManager getInstance() {

        if (instance == null) {
            instance = new ResourceManager();
        }

        return instance;
    }

    String getValue(String key) {
        return bundle.getString(key);
    }
}
