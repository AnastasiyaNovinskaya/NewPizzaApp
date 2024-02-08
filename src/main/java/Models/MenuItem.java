package Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MenuItem {
    private final PizzaType pizzaType;
    private final IngredientType ingredientType;
    private final String selectedPizzaId;
    private final Connection connection;

    public MenuItem(Connection connection, PizzaType pizzaType, IngredientType ingredientType, String selectedPizzaId) {
        this.connection = connection;
        this.pizzaType = pizzaType;
        this.ingredientType = ingredientType;
        this.selectedPizzaId = selectedPizzaId;
    }

    public void addOrder(String ingredientName, double ingredientPrice, double pizzaPrice) throws SQLException {
        String sendOrder = "INSERT INTO pizza.ingredientOrder (ingredientName, ingredientPrice, pizzaType, pizzaPrice) VALUES (?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sendOrder)) {
            preparedStatement.setString(1, ingredientName);
            preparedStatement.setDouble(2, ingredientPrice);
            preparedStatement.setString(3, pizzaType.toString());
            preparedStatement.setDouble(4, pizzaPrice);

            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            throw new SQLException("Failed to add order: " + exception.getMessage());
        }
    }
}
