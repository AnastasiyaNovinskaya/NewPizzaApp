package Servlets;

import Models.IngredientType;
import Models.PizzaType;
import Models.MenuItem;
import Repository.Repository;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "SelectIngredientServlet", value = "/selectIngredient")
public class SelectIngredientServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private Repository repository;

    @Override
    public void init() {
        repository = new Repository();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ArrayList<IngredientType> ingredientTypeArrayList = repository.getIngredient();
        HttpSession session = request.getSession(true);
        session.setAttribute("ingredients", ingredientTypeArrayList);
        request.setAttribute("ingredients", ingredientTypeArrayList);

        // Forwarding to the ingredient selection page
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/selectIngredient.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        // Retrieving selected pizza from session
        HttpSession session = request.getSession();
        String selectedPizzaId = (String) session.getAttribute("selectedPizzaId");

        // Handling ingredient selection
        String[] selectedIngredients = request.getParameterValues("selectedIngredients");
        if (selectedIngredients != null) {
            for (String ingredientId : selectedIngredients) {
                request.getParameter("ingredientQuantity" + ingredientId);
                IngredientType ingredientType = repository.getIngredientById(Integer.parseInt(ingredientId));

                Connection connection = repository.getConnection();

                try {
                    PizzaType pizzaType = repository.getPizzaById(Integer.parseInt(selectedPizzaId));
                    MenuItem menuItem = new MenuItem(connection, pizzaType, ingredientType, selectedPizzaId);
                    menuItem.addOrder(ingredientType.getName(), ingredientType.getPrice(), pizzaType.getPrice());
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        // Redirecting to welcome page
        response.sendRedirect(request.getContextPath() + "/welcome.jsp");
    }

    @Override
    public void destroy() {
        repository.closeConnection();
    }
}
