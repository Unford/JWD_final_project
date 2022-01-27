package by.epam.bartenderhelper.model.util;

import java.util.Locale;
import java.util.ResourceBundle;

public enum ResourceManager {//fixme
    APP_CONFIG(ResourceBundle.getBundle("config/app_config")),
    MESSAGE_RU(ResourceBundle.getBundle("page_content", new Locale("ru"))),
    MESSAGE_EN(ResourceBundle.getBundle("page_content", new Locale("en")));

    private ResourceBundle bundle;
    ResourceManager(ResourceBundle bundle) {
        this.bundle = bundle;
    }

    public String getString(String key) { return bundle.getString(key);
    }
}
