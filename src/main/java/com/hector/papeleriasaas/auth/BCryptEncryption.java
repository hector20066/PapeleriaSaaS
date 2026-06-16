package com.hector.papeleriasaas.auth;

import org.mindrot.jbcrypt.BCrypt;

public class BCryptEncryption {

    public static String encryptPassword(String plainPassword) {
        return BCrypt.hashpw(plainPassword, BCrypt.gensalt(12));
    }

    public static boolean verifyPassword(String plainPassword, String encryptPassword) {
        return BCrypt.checkpw(plainPassword, encryptPassword);
    }

}
