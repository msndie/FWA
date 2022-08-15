package edu.school21.cinema.filters;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebFilter(filterName = "AuthenticationFilter", urlPatterns = {"/profile", "/signIn", "/signUp", "/images/*"})
public class AuthenticationFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        String path = req.getServletPath();
        Object obj = req.getSession().getAttribute("UserAttributes");
        if (obj == null && path.equals("/profile")) {
            res.sendError(403, "Forbidden");
        } else if (obj != null && (path.equals("/signIn") || path.equals("/signUp"))) {
            res.sendRedirect("profile");
        }
        chain.doFilter(request, response);
    }
}
