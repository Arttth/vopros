package org.arta.vopros.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import lombok.SneakyThrows;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebFilter("/*")
public class CharsetFilter implements Filter {
    @Override
    @SneakyThrows
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) {
        request.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        filterChain.doFilter(request, response);
    }
}
