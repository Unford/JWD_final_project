package test.epam.bartenderhelper.validator;

import by.epam.bartenderhelper.model.validator.IngredientFormValidator;
import by.epam.bartenderhelper.model.validator.UserFormValidator;
import by.epam.bartenderhelper.model.validator.impl.IngredientFormValidatorImpl;
import by.epam.bartenderhelper.model.validator.impl.UserFormValidatorImpl;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class IngredientFormValidatorImplTest {
    private final IngredientFormValidator validator = IngredientFormValidatorImpl.getInstance();

    @Test(dataProvider = "dataForNameValidator")
    public void testIsNameValid(String name, boolean expected) {
        boolean actual = validator.isNameValid(name);
        Assert.assertEquals(actual, expected);
    }

    @DataProvider(name = "dataForNameValidator")
    public Object[][] dataForNameValidator() {
        return new Object[][]{
                {"", false},
                {null, false},
                {"123", false},
                {"<user>", false},
                {"Ingredient name", true},
                {"Ingredient", true},
        };
    }

    @Test(dataProvider = "dataForDescriptionValidator")
    public void testIsDescriptionValid(String description, boolean expected) {
        boolean actual = validator.isDescriptionValid(description);
        Assert.assertEquals(actual, expected);
    }

    @DataProvider(name = "dataForDescriptionValidator")
    public Object[][] dataForDescriptionValidator() {
        return new Object[][]{
                {"", false},
                {null, false},
                {"                  ", false},
                {"         text         ", true},

        };
    }

    @Test(dataProvider = "dataForMeasureValidator")
    public void testIsMeasureIdValid(String measureId, boolean expected) {
        boolean actual = validator.isMeasureIdValid(measureId);
        Assert.assertEquals(actual, expected);
    }

    @DataProvider(name = "dataForMeasureValidator")
    public Object[][] dataForMeasureValidator() {
        return new Object[][]{
                {"", false},
                {null, false},
                {"text", false},
                {"-21", false},
                {"1", true},
        };
    }

    @Test(dataProvider = "dataForPriceValidator")
    public void testIsPriceValid(String price, boolean expected) {
        boolean actual = validator.isPriceValid(price);
        Assert.assertEquals(actual, expected);
    }

    @DataProvider(name = "dataForPriceValidator")
    public Object[][] dataForPriceValidator() {
        return new Object[][]{
                {"", false},
                {null, false},
                {"text", false},
                {"-21", false},
                {"1", true},
                {"1.3", true},
                {"125.12", true},
        };
    }

    @Test(dataProvider = "dataForCalorieValidator")
    public void testIsCalorieValid(String calorie, boolean expected) {
        boolean actual = validator.isCalorieValid(calorie);
        Assert.assertEquals(actual, expected);
    }

    @DataProvider(name = "dataForCalorieValidator")
    public Object[][] dataForCalorieValidator() {
        return new Object[][]{
                {"", false},
                {null, false},
                {"text", false},
                {"-21", false},
                {"1.3", false},
                {"1", true},
                {"1200", true},
        };
    }
}