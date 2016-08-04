package com.moracle.webticketsystem.model;

import org.mindrot.jbcrypt.BCrypt;

/**
 * Created by dmitry on 6/29/2016.
 */
public class PasswordUtil {

    public static String hashPassword(String passwordPlaintext) {
        return BCrypt.hashpw(passwordPlaintext, BCrypt.gensalt());
    }

    public static boolean checkPassword(String passwordPlaintext, String storedHash) {
        return BCrypt.checkpw(passwordPlaintext, storedHash);
    }
}
