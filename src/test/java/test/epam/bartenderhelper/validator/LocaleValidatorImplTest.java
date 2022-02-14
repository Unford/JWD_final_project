package test.epam.bartenderhelper.validator;

import by.epam.bartenderhelper.model.validator.LocaleValidator;
import by.epam.bartenderhelper.model.validator.UserFormValidator;
import by.epam.bartenderhelper.model.validator.impl.LocaleValidatorImpl;
import by.epam.bartenderhelper.model.validator.impl.UserFormValidatorImpl;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class LocaleValidatorImplTest {

    private final LocaleValidator validator = LocaleValidatorImpl.getInstance();


    @Test(dataProvider = "dataForLocaleValidator")
    public void testIsLocaleValid(String locale, boolean expected) {
        boolean actual = validator.isLocaleValid(locale);
        Assert.assertEquals(actual, expected);
    }

    @DataProvider(name = "dataForLocaleValidator")
    public Object[][] dataForLocaleValidator() {
        return new Object[][]{
                {"", false},
                {null, false},
                {"ru", true},
                {"en", true},
        };
    }
}