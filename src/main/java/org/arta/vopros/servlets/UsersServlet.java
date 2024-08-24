package org.arta.vopros.servlets;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.arta.vopros.db.impl.UserDao;
import org.arta.vopros.domain.User;
import org.arta.vopros.dto.UserFilter;
import org.arta.vopros.exception.ServletResponseException;
import org.arta.vopros.services.UserService;


import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;

@WebServlet(name = "UsersServlet", value = "/users")
public class UsersServlet extends HttpServlet {
    private static final UserService userService = UserService.getInstance();
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html");
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());

        try (PrintWriter writer = response.getWriter()) {
            writer.write("<ul>");
            userService.findAll().stream().forEach(flightDto -> {
                writer.write("""
                        <li>
                        <a href='/user?userId=%d'>%s</a>
                        </li>
                        """.formatted(flightDto.userId(), flightDto.userDescription()));
            });
            writer.write("</ul>");
        } catch (IOException e) {
            throw new ServletResponseException(e);
        }
    }
}
