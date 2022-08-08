package edu.school21.cinema.servlets;

import org.springframework.context.ApplicationContext;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "Profile", value = "/profile")
public class Profile extends HttpServlet {

    private String path;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        ApplicationContext springContext = (ApplicationContext) context.getAttribute("SpringContext");
        path = context.getRealPath("") + springContext.getBean("getStoragePath", String.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (response.isCommitted()) {
            return;
        }
        request.getSession().setAttribute("Path", path);
        RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/jsp/profile.jsp");
        view.forward(request, response);
    }
}
