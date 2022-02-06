package by.epam.bartenderhelper.model.util;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * The enum Resource manager.
 */
public enum ResourceManager {//fixme
    /**
     * App config resource manager.
     */
    APP_CONFIG(ResourceBundle.getBundle("config/app_config")),
    /**
     * The Message ru.
     */
    MESSAGE_RU(ResourceBundle.getBundle("page_content", new Locale("ru"))),
    /**
     * The Message en.
     */
    MESSAGE_EN(ResourceBundle.getBundle("page_content", new Locale("en")));

    private ResourceBundle bundle;
    ResourceManager(ResourceBundle bundle) {
        this.bundle = bundle;
    }

    /**
     * Gets string.
     *
     * @param key the key
     * @return the string
     */
    public String getString(String key) { return bundle.getString(key);
    }
}
