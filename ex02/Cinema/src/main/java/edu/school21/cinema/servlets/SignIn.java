package edu.school21.cinema.servlets;

import edu.school21.cinema.models.Session;
import edu.school21.cinema.models.User;
import edu.school21.cinema.services.SessionService;
import edu.school21.cinema.services.UserService;
import org.springframework.context.ApplicationContext;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.net.InetAddress;
import java.sql.Timestamp;

@WebServlet(name = "SignIn", value = "/signIn")
public class SignIn extends HttpServlet {

    private UserService userService;
    private SessionService sessionService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        ApplicationContext springContext = (ApplicationContext) context.getAttribute("SpringContext");
        userService = springContext.getBean(UserService.class);
        sessionService = springContext.getBean(SessionService.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        if (res.isCommitted()) {
            return;
        }
        RequestDispatcher view = req.getRequestDispatcher("/WEB-INF/html/signIn.html");
        view.forward(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        if (req.getSession().getAttribute("UserAttributes") != null) {
            return;
        }
        User user = new User(null, null, null, null, req.getParameter("pass"), req.getParameter("email"));
        if (userService.signIn(user)) {
            req.getSession().setAttribute("UserAttributes", user);
            String remoteAddr = req.getRemoteAddr();
            if (remoteAddr.equals("0:0:0:0:0:0:0:1")) {
                InetAddress localip = java.net.InetAddress.getLocalHost();
                remoteAddr = localip.getHostAddress();
            }
            Session session = new Session(new Timestamp(System.currentTimeMillis()),
                    remoteAddr, user.getId());
            sessionService.addSession(session);
            req.getSession().setAttribute("SessionAttributes", sessionService.getAllSessionsByUserId(user.getId()));
            res.sendRedirect("profile");
        } else {
            RequestDispatcher view = req.getRequestDispatcher("/WEB-INF/html/signIn.html");
            view.forward(req, res);
        }
    }
}
