package Servlets;

import Models.IngredientType;
import Models.PizzaType;
import Repository.Repository;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;


@WebServlet(name = "SelectIngredientServlet", value = "/selectIngredient")
public class SelectIngredientServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Repository repository = new Repository();

        ArrayList<IngredientType> ingredientTypeArrayList = repository.getIngredient();
        HttpSession session = request.getSession(true);
        session.setAttribute("ingredients", ingredientTypeArrayList);
        request.setAttribute("ingredients", ingredientTypeArrayList);

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/selectIngredient.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String[] selectedIngredients = request.getParameterValues("selectedIngredients");

        Repository repository = new Repository();
        double totalPrice = 0;
        if (selectedIngredients != null) {
            for (String ingredientId : selectedIngredients) {
                String ingredientQuantities = request.getParameter("ingredientQuantity" + ingredientId);
                int quantity = Integer.parseInt(ingredientQuantities);
                IngredientType ingredient = repository.getIngredientById(Integer.parseInt(ingredientId));

                double price = ingredient.getPrice();
                totalPrice = price * quantity;

                repository.addIngredientOrder(ingredient.getName(), quantity, totalPrice);
            }
        }
        response.sendRedirect(request.getContextPath() + "/welcome.jsp");
    }
}
