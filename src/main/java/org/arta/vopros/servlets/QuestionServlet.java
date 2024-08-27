package org.arta.vopros.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.arta.vopros.exception.ServletResponseException;
import org.arta.vopros.services.QuestionService;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

@WebServlet(name="QuestionServlet", value = "/questions")
public class QuestionServlet extends HttpServlet {
    private static final QuestionService questionService = QuestionService.getInstance();
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        response.setContentType("text/html");
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        try (var writer = response.getWriter()) {
            Long userId = Long.valueOf(request.getParameter("userId"));
            writer.write("<h1>Вопросы</h1>");
            questionService.findAllByUserId(userId).stream().forEach(questionDto -> {
                writer.write("""
                        <li>
                        <a href='/user?userId=%d'>%s</a>
                        </li>
                        """.formatted(questionDto.questionId(), questionDto.questionMainPart()));
            });
            writer.write("</ul>");
        } catch (IOException e) {
            throw new ServletResponseException(e);
        }
    }
}
