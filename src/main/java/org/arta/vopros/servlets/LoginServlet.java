package org.arta.vopros.servlets;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import org.arta.vopros.dto.UserDto;
import org.arta.vopros.services.UserService;
import org.arta.vopros.utils.JspHelper;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final UserService userService = UserService.getInstance();
    @Override
    @SneakyThrows
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        request.getRequestDispatcher(JspHelper.getPath("login")).forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        userService.login(request.getParameter("email"), request.getParameter("password"))
                .ifPresentOrElse(userDto -> onLoginSuccess(userDto, request, response), () -> onLoginFail(request, response));

    }

    @SneakyThrows
    private void onLoginSuccess(UserDto userDto, HttpServletRequest request, HttpServletResponse response) {
        request.getSession().setAttribute("user", userDto);
        response.sendRedirect("/user");
    }
    @SneakyThrows
    private void onLoginFail(HttpServletRequest request, HttpServletResponse response) {
        response.sendRedirect("/login?email=" + request.getParameter("email"));
    }
}
