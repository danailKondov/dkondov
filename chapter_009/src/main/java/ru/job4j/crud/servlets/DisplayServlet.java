package ru.job4j.crud.servlets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.crud.model.UserStore;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet for displaying users by login.
 *
 * @since 31/01/2018
 * @version 2
 */
public class DisplayServlet extends HttpServlet {

    private static final Logger LOG = LoggerFactory.getLogger(DisplayServlet.class);
    private final UserStore store = UserStore.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect(String.format("%s/views/DisplayUser.jsp", req.getContextPath()));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        req.setAttribute("user", store.getUser(login));
        RequestDispatcher dispatcher = req.getRequestDispatcher("/views/DisplayUser.jsp");
        dispatcher.forward(req, resp);
    }
}
