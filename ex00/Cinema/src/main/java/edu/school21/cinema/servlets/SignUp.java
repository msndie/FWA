package edu.school21.cinema.servlets;

import edu.school21.cinema.models.User;
import edu.school21.cinema.services.UserService;
import org.springframework.context.ApplicationContext;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "SignUp", value = "/signUp")
public class SignUp extends HttpServlet {

    private UserService userService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        ApplicationContext springContext = (ApplicationContext) context.getAttribute("SpringContext");
        userService = springContext.getBean(UserService.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        RequestDispatcher view = req.getRequestDispatcher("/WEB-INF/html/signUp.html");
        view.forward(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        RequestDispatcher view;
        User user = new User(null,
                req.getParameter("fname"),
                req.getParameter("lname"),
                req.getParameter("phone"),
                req.getParameter("pass"),
                req.getParameter("email"));
        if (userService.signUp(user)) {
            view = req.getRequestDispatcher("/WEB-INF/html/signIn.html");
        } else {
            view = req.getRequestDispatcher("/WEB-INF/html/failedReg.html");
        }
        view.forward(req, res);
    }
}
