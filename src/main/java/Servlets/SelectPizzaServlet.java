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

        // Forwarding to the pizza selection page
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/selectPizza.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        // Handle pizza selection
        String selectedPizzaId = request.getParameter("pizzaId");
        // Store selected pizza in session
        HttpSession session = request.getSession();
        session.setAttribute("selectedPizzaId", selectedPizzaId);

        // Redirecting to the ingredient selection page
        response.sendRedirect(request.getContextPath() + "/selectIngredient");
    }

}
