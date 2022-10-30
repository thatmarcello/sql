package ru.netology.bdsql.data;

import com.github.javafaker.Faker;
import lombok.SneakyThrows;
import lombok.Value;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DataHelper {

    private static Faker faker = new Faker(new Locale("en"));

    private DataHelper() {
    }

    public static AuthInfo validUser() {
        return new AuthInfo("vasya", "qwerty123");
    }
    @Value
    public static class validVerifyCode {
        String verifyCode;
    }

    public static String invalidPass() {
        return faker.internet().password();
    }

    public static String invalidLogin() {
        return faker.name().username();
    }

    public static AuthInfo invalidUser() {
        return new AuthInfo(invalidLogin(), invalidPass());
    }


    @Value
    public static class AuthInfo {
        String login;
        String password;
    }
}