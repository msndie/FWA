package edu.school21.cinema.servlets;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "Profile", value = "/profile")
public class Profile extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (response.isCommitted()) {
            return;
        }
        RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/html/profile.html");
        view.forward(request, response);
    }
}
