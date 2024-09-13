package org.arta.vopros.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import org.arta.vopros.dto.UserDto;
import org.arta.vopros.services.UserService;

import java.io.IOException;

@MultipartConfig(location = "/tmp/images")
@WebServlet("/user/photo-upload")
public class UserPhotoUploadServlet extends HttpServlet {
    private static final UserService userService = UserService.getInstance();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Part part = req.getPart("photo");
        String userId = ((UserDto)req.getSession().getAttribute("user")).userId().toString();
        part.write(userId);
        userService.uploadPhoto("tmp/images/" + userId);
        resp.sendRedirect("/user");
    }
}
