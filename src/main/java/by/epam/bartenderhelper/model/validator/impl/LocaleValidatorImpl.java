package by.epam.bartenderhelper.model.validator.impl;

import by.epam.bartenderhelper.model.validator.LocaleValidator;

import java.util.List;

/**
 * The type Locale validator.
 */
public class LocaleValidatorImpl extends AbstractFormValidator implements LocaleValidator {
    private static LocaleValidatorImpl instance;

    private static final List<String> locales = List.of("en", "ru");

    private LocaleValidatorImpl() {
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static LocaleValidatorImpl getInstance() {
        if (instance == null) {
            instance = new LocaleValidatorImpl();
        }
        return instance;
    }

    @Override
    public boolean isLocaleValid(String locale) {
        return locale != null && locales.contains(locale);
    }
}
