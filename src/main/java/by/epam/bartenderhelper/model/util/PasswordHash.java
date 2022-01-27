package by.epam.bartenderhelper.model.util;

import com.password4j.Password;

public final class PasswordHash {
    private PasswordHash(){}


    public static String hash(String password){
        return Password.hash(password).withBCrypt().getResult();
    }

    public static boolean checkPassword(String password, String hash){
        return Password.check(password, hash).withBCrypt();
    }


}
