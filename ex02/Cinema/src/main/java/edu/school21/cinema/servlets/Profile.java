package edu.school21.cinema.servlets;

import edu.school21.cinema.models.User;
import edu.school21.cinema.services.ImageService;
import org.springframework.context.ApplicationContext;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "Profile", value = "/profile")
public class Profile extends HttpServlet {

    private ImageService imageService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        ApplicationContext springContext = (ApplicationContext) context.getAttribute("SpringContext");
        imageService = springContext.getBean(ImageService.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (response.isCommitted()) {
            return;
        }
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("UserAttributes");
        session.setAttribute("Images", imageService.getAllImagesByUserId(user.getId()));
        RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/jsp/profile.jsp");
        view.forward(request, response);
    }
}
