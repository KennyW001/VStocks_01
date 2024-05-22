//package com.example.vstocks_01;

import static org.junit.jupiter.api.Assertions.*;

import com.example.vstocks_01.database;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseConnectionTest {

    private static Connection connection;

    @BeforeAll
    public static void setup() {
        connection = database.connectDB();
        assertNotNull(connection, "Connection should not be null");
    }

    @AfterAll
    public static void tearDown() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }

    @Test
    public void testInsertStockData() {
        String testSymbol = "TEST";
        double testPrice = 123.45;

        database.insertStockData(connection, testSymbol, testPrice);

        String sql = "SELECT * FROM login_schema.stock_data WHERE symbol = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, testSymbol);
            ResultSet resultSet = statement.executeQuery();
            assertTrue(resultSet.next(), "Result set should not be empty");
            assertEquals(testSymbol, resultSet.getString("symbol"), "Symbols should match");
            assertEquals(testPrice, resultSet.getDouble("price"), "Prices should match");
        } catch (SQLException e) {
            fail("SQL exception occurred: " + e.getMessage());
        }
    }
}
