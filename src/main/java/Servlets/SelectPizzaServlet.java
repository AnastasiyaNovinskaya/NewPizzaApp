package Servlets;

import Models.PizzaType;
import Repository.Repository;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;


@WebServlet(name = "SelectPizzaServlet", value = "/selectPizza")
public class SelectPizzaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Repository repository = new Repository();

        ArrayList<PizzaType> pizzaTypeArrayList = repository.getPizza();
        HttpSession session = request.getSession(true);
        session.setAttribute("pizzas", pizzaTypeArrayList);
        request.setAttribute("pizzas", pizzaTypeArrayList);

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/selectPizza.jsp");
        dispatcher.forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String[] selectedPizzas = request.getParameterValues("selectedPizzas");

        Repository repository = new Repository();
        double totalPrice = 0;
        if (selectedPizzas != null) {
            for (String pizzaId : selectedPizzas) {
                String pizzaQuantities = request.getParameter("pizzaQuantity" + pizzaId);
                int quantity = Integer.parseInt(pizzaQuantities);
                PizzaType pizza = repository.getPizzaById(Integer.parseInt(pizzaId));

                double price = pizza.getPrice();
                totalPrice = price * quantity;

                repository.addPizzaOrder(pizza.getName(), quantity, totalPrice);
            }
        }
        response.sendRedirect(request.getContextPath() + "/selectIngredient");
    }
}
