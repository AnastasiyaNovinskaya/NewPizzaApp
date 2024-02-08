package Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;


public class MenuItem {
    private final PizzaType pizzaType;
    private final IngredientType ingredientType;
    private final String selectedPizzaId;
    private final Connection connection;

    public MenuItem(Connection connection, PizzaType pizzaType, IngredientType ingredientType, String selectedPizzaId) {
        this.connection = connection;
        this.pizzaType = pizzaType;
        this.ingredientType = ingredientType;
        this.selectedPizzaId = selectedPizzaId != null ? selectedPizzaId : "";
    }

    public void addOrder(String pizzaType, double pizzaPrice, String ingredientName, double ingredientPrice) throws SQLException {
        // Check if pizzaType is valid
        if (!Arrays.asList("pepperoni", "margherita", "hawaii", "prosciutto", "vegetarian", "seafood").contains(pizzaType)) {
            throw new IllegalArgumentException("Invalid pizza type: " + pizzaType);
        }

        String sendOrder = "INSERT INTO pizza.orders (pizzaType, pizzaPrice, ingredientName, ingredientPrice) VALUES (?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sendOrder)) {
            preparedStatement.setString(1, pizzaType);
            preparedStatement.setDouble(2, pizzaPrice);
            preparedStatement.setString(3, ingredientName);
            preparedStatement.setDouble(4, ingredientPrice);

            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            throw new SQLException("Failed to add order: " + exception.getMessage());
        }
    }

}
