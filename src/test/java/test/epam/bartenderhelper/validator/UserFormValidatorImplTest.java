package test.epam.bartenderhelper.validator;

import by.epam.bartenderhelper.model.validator.UserFormValidator;
import by.epam.bartenderhelper.model.validator.impl.UserFormValidatorImpl;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class UserFormValidatorImplTest {
    private final UserFormValidator validator = UserFormValidatorImpl.getInstance();

    @Test(dataProvider = "dataForUsernameValidator")
    public void testIsUsernameValid(String username, boolean expected) {
        boolean actual = validator.isUsernameValid(username);
        Assert.assertEquals(actual, expected);
    }

    @DataProvider(name = "dataForUsernameValidator")
    public Object[][] dataForUsernameValidator() {
        return new Object[][]{
                {"", false},
                {null, false},
                {"1234567890123456789012345678901234567890", false},
                {"<user1", false},
                {"user 1", false},
                {"User1", true},
        };
    }

    @Test(dataProvider = "dataForPasswordValidator")
    public void testIsPasswordValid(String password, boolean expected) {
        boolean actual = validator.isPasswordValid(password);
        Assert.assertEquals(actual, expected);
    }

    @DataProvider(name = "dataForPasswordValidator")
    public Object[][] dataForPasswordValidator() {
        return new Object[][]{
                {"", false},
                {null, false},
                {"123456789012345678901234567890123456789002302312", false},
                {"<user1", false},
                {"user 1", false},
                {"User1", false},
                {"User2221", true},

        };
    }

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
                {"123456789012345678901234567890123456789002302312", false},
                {"anton", false},
                {"Anton 1", false},
                {"Anton", true},
                {"Nikita", true},

        };
    }

    @Test(dataProvider = "dataForEmailValidator")
    public void testIsEmailValid(String email, boolean expected) {
        boolean actual = validator.isEmailValid(email);
        Assert.assertEquals(actual, expected);
    }

    @DataProvider(name = "dataForEmailValidator")
    public Object[][] dataForEmailValidator() {
        return new Object[][]{
                {"", false},
                {null, false},
                {"123.@ mail.ru", false},
                {"text@.com", false},
                {"text@1.tu", true},
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
                {"", true},
                {null, false},
                {"                  ", false},
        };
    }

    @Test(dataProvider = "dataForIdValidator")
    public void testIsIdValid(String id, boolean expected) {
        boolean actual = validator.isIdValid(id);
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

    @Test(dataProvider = "dataForUserProfileValidator")
    public void testIsUserProfileValid(String login, boolean expected) {
        boolean actual = validator.isUserProfileValid(login);
        Assert.assertEquals(actual, expected);
    }

    @DataProvider(name = "dataForUserProfileValidator")
    public Object[][] dataForUserProfileValidator() {
        return new Object[][]{
                {"", false},
                {null, false},
                {"-2", false},
                {"username", true},
                {"12", true},
                {"53", true},
        };
    }

    @Test(dataProvider = "dataForUserStatusValidator")
    public void testIsUserStatusValid(String status, boolean expected) {
        boolean actual = validator.isUserStatusValid(status);
        Assert.assertEquals(actual, expected);
    }

    @DataProvider(name = "dataForUserStatusValidator")
    public Object[][] dataForUserStatusValidator() {
        return new Object[][]{
                {"", false},
                {null, false},
                {"working", false},
                {"banned", false},
                {"WORKING", true},
                {"BANNED", true},
        };
    }

    @Test(dataProvider = "dataForUserRoleValidator")
    public void testIsUserRoleValid(String role, boolean expected) {
        boolean actual = validator.isUserRoleValid(role);
        Assert.assertEquals(actual, expected);
    }

    @DataProvider(name = "dataForUserRoleValidator")
    public Object[][] dataForUserRoleValidator() {
        return new Object[][]{
                {"", false},
                {null, false},
                {"admin", false},
                {"client", false},
                {"CLIENT", true},
                {"ADMIN", true},
                {"GUEST", true},
                {"BARTENDER", true},

        };
    }
}