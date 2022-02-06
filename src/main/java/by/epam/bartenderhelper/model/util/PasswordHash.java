package by.epam.bartenderhelper.model.util;

import com.password4j.Password;

/**
 * The type Password hash.
 */
public final class PasswordHash {
    private PasswordHash(){}


    /**
     * Hash string.
     *
     * @param password the password
     * @return the string
     */
    public static String hash(String password){
        return Password.hash(password).withBCrypt().getResult();
    }

    /**
     * Check password boolean.
     *
     * @param password the password
     * @param hash     the hash
     * @return the boolean
     */
    public static boolean checkPassword(String password, String hash){
        return Password.check(password, hash).withBCrypt();
    }


}
