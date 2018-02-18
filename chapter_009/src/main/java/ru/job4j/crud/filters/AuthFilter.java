package ru.job4j.crud.filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created on 13.02.2018.
 */
public class AuthFilter implements Filter{

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        // пропускаем запрос на аутентификацию без фильтрации и проверки
        if (request.getRequestURI().contains("/sign") || request.getRequestURI().contains("resources/images")) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            HttpSession session = request.getSession();
            if (session.getAttribute("login") == null) {
                response.sendRedirect(String.format("%s/sign", request.getContextPath())); // to sign in page
            }
            filterChain.doFilter(servletRequest, servletResponse);
        }
//        filterChain.doFilter(servletRequest, servletResponse);
    }
}
