package test.epam.bartenderhelper.validator;

import by.epam.bartenderhelper.model.validator.ReviewFormValidator;
import by.epam.bartenderhelper.model.validator.UserFormValidator;
import by.epam.bartenderhelper.model.validator.impl.ReviewFormValidatorImpl;
import by.epam.bartenderhelper.model.validator.impl.UserFormValidatorImpl;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ReviewFormValidatorImplTest {
    private final ReviewFormValidator validator = ReviewFormValidatorImpl.getInstance();



    @Test(dataProvider = "dataForIdValidator")
    public void testIsTargetIdValid(String id, boolean expected) {
        boolean actual = validator.isTargetIdValid(id);
        Assert.assertEquals(actual, expected);
    }

    @DataProvider(name = "dataForIdValidator")
    public Object[][] dataForIdValidator() {
        return new Object[][]{
                {"", false},
                {null, false},
                {"text", false},
                {"-2", false},
                {"12", true},
                {"53", true},
        };
    }

    @Test(dataProvider = "dataForRatingValidator")
    public void testIsRatingValid(String rating, boolean expected) {
        boolean actual = validator.isRatingValid(rating);
        Assert.assertEquals(actual, expected);
    }

    @DataProvider(name = "dataForRatingValidator")
    public Object[][] dataForRatingValidator() {
        return new Object[][]{
                {"", false},
                {null, false},
                {"text", false},
                {"0.1", false},
                {"10", false},
                {"3.4", false},
                {"5.5", false},
                {"0.0", false},
                {"1", true},
                {"5.0", true},
                {"5", true},
        };
    }

    @Test(dataProvider = "dataForMessageValidator")
    public void testIsMessageValid(String message, boolean expected) {
        boolean actual = validator.isMessageValid(message);
        Assert.assertEquals(actual, expected);
    }

    @DataProvider(name = "dataForMessageValidator")
    public Object[][] dataForMessageValidator() {
        return new Object[][]{
                {"", false},
                {null, false},
                {"                  ", false},
                {"text     text", true},

        };
    }
}