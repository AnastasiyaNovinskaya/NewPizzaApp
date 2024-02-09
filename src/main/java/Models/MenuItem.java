package Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;

public class MenuItem {
    private Connection connection;

    public MenuItem(Connection connection) {
        this.connection = connection;
    }

    public void addOrder(String pizzaType, double pizzaPrice, int pizzaQuantity, String ingredientName, double ingredientPrice, int ingredientQuantity) throws SQLException {
        // Check if pizzaType is valid
        if (!Arrays.asList("pepperoni", "margherita", "hawaii", "prosciutto", "vegetarian", "seafood").contains(pizzaType)) {
            throw new IllegalArgumentException("Invalid pizza type: " + pizzaType);
        }

        String sendOrder = "INSERT INTO pizza.orders (pizzaType, pizzaPrice, pizzaQuantity, ingredientName, ingredientPrice, ingredientQuantity) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sendOrder)) {
            preparedStatement.setString(1, pizzaType);
            preparedStatement.setDouble(2, pizzaPrice);
            preparedStatement.setInt(3, pizzaQuantity);
            preparedStatement.setString(4, ingredientName);
            preparedStatement.setDouble(5, ingredientPrice);
            preparedStatement.setInt(6, ingredientQuantity);

            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            throw new SQLException("Failed to add order: " + exception.getMessage());
        }
    }

}
