package by.epam.bartenderhelper.model.util;

import java.util.ResourceBundle;

public enum PropertyManager {//fixme
    APP_CONFIG(ResourceBundle.getBundle("config/app_config"));

    private ResourceBundle bundle;
    PropertyManager(ResourceBundle bundle) {
        this.bundle = bundle;
    }

    public String getString(String key) { return bundle.getString(key);
    }
}
