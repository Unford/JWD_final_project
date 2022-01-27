package test.epam.bartenderhelper.hash;

import by.epam.bartenderhelper.model.util.PasswordHash;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PasswordHashTest {

    @Test
    public void hashTest(){//todo delete this
        String s = "ExamplePassword";
        var r = PasswordHash.hash(s);
        Assert.assertTrue(PasswordHash.checkPassword(s, r));
        Assert.assertFalse(PasswordHash.checkPassword("New", r));
    }
}
