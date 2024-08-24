package org.arta.vopros.servlets;

import jakarta.servlet.annotation.WebServlet;
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html");
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());

        try (var writer = response.getWriter()) {
            writer.write("<h1>Вопросы</h1>");
            questionService.findAll().stream().forEach(questionDto -> {
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
