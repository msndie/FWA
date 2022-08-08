package edu.school21.cinema.servlets;

import edu.school21.cinema.models.User;
import edu.school21.cinema.services.UserService;
import org.springframework.context.ApplicationContext;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "SignIn", value = "/signIn")
public class SignIn extends HttpServlet {

    private UserService userService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        ApplicationContext springContext = (ApplicationContext) context.getAttribute("SpringContext");
        userService = springContext.getBean(UserService.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        RequestDispatcher view = req.getRequestDispatcher("/WEB-INF/html/signIn.html");
        view.forward(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        RequestDispatcher view;
        User user = new User(null, null, null, null, req.getParameter("pass"), req.getParameter("email"));
        if (userService.signIn(user)) {
            view = req.getRequestDispatcher("/WEB-INF/html/profile.html");
            req.getSession().setAttribute("UserAttributes", user);
        } else {
            view = req.getRequestDispatcher("/WEB-INF/html/signIn.html");
        }
        view.forward(req, res);
    }
}
