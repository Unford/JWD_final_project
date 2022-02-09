package by.epam.bartenderhelper.model.validator;

/**
 * The interface Locale validator.
 */
public interface LocaleValidator extends FormValidator{
    /**
     * Is locale valid boolean.
     *
     * @param locale the locale
     * @return the boolean
     */
    boolean isLocaleValid(String locale);
}
