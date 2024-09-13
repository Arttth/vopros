package org.arta.vopros.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.arta.vopros.utils.JspHelper;

import java.io.IOException;

@WebServlet(name="QuestionCreateServlet", value = "/question-create")
public class QuestionCreateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(JspHelper.getPath("create_question")).forward(req, resp);
    }
}
