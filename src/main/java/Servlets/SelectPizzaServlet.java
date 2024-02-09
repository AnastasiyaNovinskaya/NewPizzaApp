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
            throws IOException, ServletException {
        String selectedPizzaId = request.getParameter("pizzaId");
        LOGGER.info("Selected Pizza ID: " + selectedPizzaId); // Print selected pizza ID to log

        // Store chosen values in log
        LOGGER.info("Values chosen by you are stored: selectedPizzaId=" + selectedPizzaId);

        HttpSession session = request.getSession();
        session.setAttribute("selectedPizzaId", selectedPizzaId);

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