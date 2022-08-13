package edu.school21.cinema.servlets;

import edu.school21.cinema.models.Image;
import edu.school21.cinema.models.User;
import edu.school21.cinema.services.ImageService;
import edu.school21.cinema.utils.Utils;
import org.springframework.context.ApplicationContext;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;

@WebServlet(name = "Images", value = "/images/*")
@MultipartConfig
public class Images extends HttpServlet {

    private String path;
    private ImageService imageService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        ApplicationContext springContext = (ApplicationContext) context.getAttribute("SpringContext");
        path = springContext.getBean("getStoragePath", String.class);
        imageService = springContext.getBean(ImageService.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getPathInfo() != null) {
            String[] paths = request.getPathInfo().split("/");
            if (paths.length == 2) {
                Image image = null;
                UUID uuid = null;
                try {
                    uuid = UUID.fromString(paths[1]);
                } catch (IllegalArgumentException ignored) {}
                if (uuid != null) {
                     image = imageService.getByUUID(uuid);
                }
                if (image != null) {
                    response.setStatus(HttpServletResponse.SC_OK);
                    response.setContentType(image.getMime());
                    response.setContentLengthLong(image.getSize());
                    try (FileInputStream fis = new FileInputStream(path + image.getUuid() + "." + image.getMime().split("/")[1])) {
                        Utils.copyData(fis, response.getOutputStream());
                        response.getOutputStream().close();
                    } catch (FileNotFoundException e) {
                        response.sendError(HttpServletResponse.SC_NOT_FOUND, "Image not found! Probably it does not exist any more)))");
                        imageService.deleteImage(image);
                    }
                    return;
                }
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Image not found!");
                return;
            }
        }
        response.sendError(HttpServletResponse.SC_BAD_REQUEST);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("UserAttributes");
        Part filePart = request.getPart("file");
        String fileName = filePart.getSubmittedFileName();
        String mime = filePart.getContentType();
        UUID uuid = UUID.randomUUID();
        for (Part part : request.getParts()) {
            part.write(path + uuid + "." + mime.split("/")[1]);
        }
        imageService.addImage(new Image(user.getId(), uuid, fileName, mime, filePart.getSize()));
        response.sendRedirect(request.getContextPath() + "/profile");
    }
}
