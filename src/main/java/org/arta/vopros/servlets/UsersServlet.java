package org.arta.vopros.servlets;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.arta.vopros.domain.User;
import org.arta.vopros.utils.ConnectionManager;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet(name = "UsersServlet", value = "/users-servlet")
public class UsersServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        try (var connection = ConnectionManager.open()){
            System.out.println(connection.getTransactionIsolation());
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM users");
            rs.next();
            PrintWriter out = response.getWriter();
            out.println("<html><body>");
            out.println("<h1>" + rs.getString("user_name") + "</h1>");
            out.println("</body></html>");
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
//            connection.close();
        }
    }
}
