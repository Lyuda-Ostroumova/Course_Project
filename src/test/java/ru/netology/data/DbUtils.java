package ru.netology.data;

import lombok.SneakyThrows;
import lombok.val;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.junit.jupiter.api.BeforeAll;

import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtils {

    @BeforeAll
    static void setUp() {

    }

    static String url = getUrl();
    static String user = "app";
    static String password = "pass";

    public static String getUrl() {
        return System.getProperty("db.url");
    }

    @SneakyThrows
    public static void cleanTable() {
        val deletePaymentInfo = "DELETE FROM payment_entity; ";
        val runner = new QueryRunner();
        try (val conn = DriverManager.getConnection(url, user, password)) {
            runner.update(conn, deletePaymentInfo);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    @SneakyThrows
    public static String getStatusOfPayment() {
        String statusSQL = "SELECT status FROM payment_entity";
        return getStatus(statusSQL);
    }

    @SneakyThrows
    private static String getStatus(String query) {
        val runner = new QueryRunner();
        try (val conn = DriverManager.getConnection(url, user, password)) {
            String status = runner.query(conn, query, new ScalarHandler<String>());
            return status;
        }
    }
}
