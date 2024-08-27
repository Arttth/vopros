package org.arta.vopros.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.arta.vopros.dto.CreateUserDto;
import org.arta.vopros.exception.ValidationException;
import org.arta.vopros.services.UserService;
import org.arta.vopros.utils.JspHelper;

import java.io.IOException;

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {
    private static final UserService userService = UserService.getInstance();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        request.getRequestDispatcher(JspHelper.getPath("registration")).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        CreateUserDto createUserDto = CreateUserDto.builder()
                .name(request.getParameter("name"))
                .lastname(request.getParameter("lastname"))
                .dateOfBirth(request.getParameter("birthday"))
                .email(request.getParameter("email"))
                .password(request.getParameter("password"))
                .nickname(request.getParameter("nickname"))
                .build();

        try {
            userService.create(createUserDto);
            response.sendRedirect("/login");
        } catch (ValidationException e) {
            request.setAttribute("errors", e.getErrors());
            doGet(request, response);
        }

    }
}
