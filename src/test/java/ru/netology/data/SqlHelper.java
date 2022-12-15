package ru.netology.data;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import ru.netology.models.CreditRequestEntity;
import ru.netology.models.PaymentEntity;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class SqlHelper {
    private static final String url = System.getProperty("db.url");
    private static final String user = System.getProperty("db.user");
    private static final String password = System.getProperty("db.password");
    private static Connection conn;

    public static Connection getConnection() {
        try {
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return conn;
    }

    public static void cleanDataBase() {
        var runner = new QueryRunner();
        var order = "DELETE FROM order_entity";
        var payment = "DELETE FROM payment_entity";
        var creditRequest = "DELETE FROM credit_request_entity";

        try (var connection = getConnection()) {
            runner.update(connection, order);
            runner.update(connection, payment);
            runner.update(connection, creditRequest);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    public static String getCardStatusForPayment() {
        var runner = new QueryRunner();
        String statusQuery = "SELECT status FROM payment_entity;";
        try (Connection connection = getConnection()) {
            var cardStatus = runner.query(connection,statusQuery,new BeanHandler<>(PaymentEntity.class));
            return cardStatus.getStatus();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return null;
    }

    public static String getCardStatusForCreditRequest() {
        var runner = new QueryRunner();
        String statusQuery = "SELECT status FROM credit_request_entity;";
        try (Connection connection = getConnection()) {
            var cardStatus = runner.query(connection, statusQuery, new BeanHandler<>(CreditRequestEntity.class));
            return cardStatus.getStatus();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return null;
    }
}


