package by.epam.bartenderhelper.model.validator.impl;

import by.epam.bartenderhelper.model.validator.LocaleValidator;

import java.util.List;

public class LocaleValidatorImpl implements LocaleValidator {
    private static LocaleValidatorImpl instance;

    private static final List<String> locales = List.of("en", "ru");

    private LocaleValidatorImpl() {
    }

    public static LocaleValidatorImpl getInstance() {
        if (instance == null) {
            instance = new LocaleValidatorImpl();
        }
        return instance;
    }

    @Override
    public boolean isLocaleValid(String locale) {
        return locales.contains(locale);
    }
}
