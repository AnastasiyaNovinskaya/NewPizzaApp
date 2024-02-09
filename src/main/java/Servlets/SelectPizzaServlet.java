package Servlets;

import Models.PizzaType;
import Repository.Repository;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Logger;

@WebServlet(name = "SelectPizzaServlet", value = "/selectPizza")
public class SelectPizzaServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(SelectPizzaServlet.class.getName());

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String selectedPizzaId = request.getParameter("pizzaId");
        LOGGER.info("Selected Pizza ID: " + selectedPizzaId); // Print selected pizza ID to log
        
        // Retrieve the quantity of the selected pizza
        String pizzaQuantityParam = request.getParameter("pizzaQuantity" + selectedPizzaId); // Modify parameter name
        int pizzaQuantity = 0; // default value if parameter is null
        if (pizzaQuantityParam != null && !pizzaQuantityParam.isEmpty()) {
            pizzaQuantity = Integer.parseInt(pizzaQuantityParam);
        }



        // Store chosen values in session
        HttpSession session = request.getSession();
        session.setAttribute("selectedPizzaId", selectedPizzaId);
        session.setAttribute("pizzaQuantity", pizzaQuantity); // Store pizza quantity in session

        response.sendRedirect(request.getContextPath() + "/selectIngredient");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Repository repository = new Repository();
        ArrayList<PizzaType> pizzaTypeArrayList = repository.getPizza();
        HttpSession session = request.getSession(true);
        session.setAttribute("pizzas", pizzaTypeArrayList);
        request.setAttribute("pizzas", pizzaTypeArrayList);

        LOGGER.info("Pizza list retrieved successfully");

        // Forwarding to the pizza selection page
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/selectPizza.jsp");
        dispatcher.forward(request, response);
    }
}