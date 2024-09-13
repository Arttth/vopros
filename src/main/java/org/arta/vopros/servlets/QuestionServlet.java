package org.arta.vopros.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.arta.vopros.dto.CreateQuestionDto;
import org.arta.vopros.dto.QuestionDto;
import org.arta.vopros.dto.UserDto;
import org.arta.vopros.exception.ServletResponseException;
import org.arta.vopros.services.QuestionService;
import org.arta.vopros.utils.JspHelper;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

@WebServlet(name="QuestionServlet", value = "/question")
public class QuestionServlet extends HttpServlet {
    private static final QuestionService questionService = QuestionService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long questionId = Long.valueOf(req.getParameter("questionId"));
        QuestionDto questionDto = questionService.findById(questionId);
        req.setAttribute("question", questionDto);
        req.getRequestDispatcher(JspHelper.getPath("question")).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userNickname = ((UserDto) req.getSession().getAttribute("user")).nickname();
        CreateQuestionDto createQuestionDto = new CreateQuestionDto(
                req.getParameter("name"),
                req.getParameter("main"),
                userNickname,
                req.getParameter("disciplineName")
        );
        questionService.create(createQuestionDto);
    }
}
