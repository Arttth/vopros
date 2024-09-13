package org.arta.vopros.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.arta.vopros.dto.QuestionDto;
import org.arta.vopros.services.QuestionService;
import org.arta.vopros.utils.JspHelper;

import java.io.IOException;
import java.util.List;

@WebServlet("/question-wall")
public class QuestionsServlet extends HttpServlet {
    private final QuestionService questionService = QuestionService.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("questions", questionService.findAll());
        req.getRequestDispatcher(JspHelper.getPath("question-wall")).forward(req, resp);
    }
}
