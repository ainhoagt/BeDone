package com.example.be_done_api.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordUtils {
    private static final BCryptPasswordEncoder BCRYPT_PASSWORD_ENCODER = new BCryptPasswordEncoder();

    public static String hashPassword(String password) {
        return BCRYPT_PASSWORD_ENCODER.encode(password);
    }
    public static boolean checkPassword(String password, String hashedPassword) {
        return BCRYPT_PASSWORD_ENCODER.matches(password, hashedPassword);
    }
}
