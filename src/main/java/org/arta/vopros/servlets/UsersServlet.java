package org.arta.vopros.servlets;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.arta.vopros.db.impl.UserDao;
import org.arta.vopros.domain.User;
import org.arta.vopros.dto.UserFilter;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "UsersServlet", value = "/users")
public class UsersServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        UserDao userDao = UserDao.getInstance();
        UserFilter userFilter = new UserFilter("rais", null, null, 3, 0);
        List<User> users = userDao.findAll(userFilter);

        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        for (User user : users) {
            out.println("<h1>" + user.getName() + "</h1>");
        }
        out.println("</body></html>");
    }
}
