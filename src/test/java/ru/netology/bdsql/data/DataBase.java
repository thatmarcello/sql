package ru.netology.bdsql.data;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.*;


public class DataBase {


    private static QueryRunner runner = new QueryRunner();

    private DataBase() {
    }

    ;

    @SneakyThrows
    private static Connection getConn() {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/app", "app", "pass");

    }


    @SneakyThrows
    public static DataHelper.validVerifyCode getValidVerifyCode() {
        var codeSQL = "SELECT code FROM auth_codes ORDER BY created DESC LIMIT 1";
        var conn = getConn();
        var result = runner.query(conn, codeSQL, new ScalarHandler<String>());
        return new DataHelper.validVerifyCode(result);
    }

    @SneakyThrows
    public static void resetBase() {
        var connection = getConn();
        runner.execute(connection, "DELETE FROM auth_codes");
        runner.execute(connection, "DELETE FROM card_transactions");
        runner.execute(connection, "DELETE FROM cards");
        runner.execute(connection, "DELETE FROM users");
    }


}