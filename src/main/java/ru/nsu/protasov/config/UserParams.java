package ru.nsu.protasov.config;

import org.springframework.stereotype.Component;

@Component
public class UserParams {
    private static String user;
    private static String password;

    public static String getUser() {
        return user;
    }

    public static void setLogin(String user) {
        UserParams.user = user;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        UserParams.password = password;
    }
}
