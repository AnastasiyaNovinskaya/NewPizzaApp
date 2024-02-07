package Servlets;

import Repository.Repository;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "RegistrationServlet", value = "/registration")
public class RegistrationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        Repository repository = new Repository();

        repository.sendRegistration(username, password);
        HttpSession session = request.getSession(true);
        session.setAttribute("username", username);
        response.sendRedirect("index.jsp");
    }
}

