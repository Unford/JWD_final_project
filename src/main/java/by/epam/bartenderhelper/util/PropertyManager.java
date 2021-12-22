package by.epam.bartenderhelper.util;

import java.util.Locale;
import java.util.ResourceBundle;

public enum PropertyManager {//fixme
    APP_CONFIG(ResourceBundle.getBundle("config/app_config", new Locale("", "")));

    private ResourceBundle bundle;
    PropertyManager(ResourceBundle bundle) {
        this.bundle = bundle;
    }

    public String getString(String key) { return bundle.getString(key);
    }
}
