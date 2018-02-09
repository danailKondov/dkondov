package ru.job4j.crud.servlets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.crud.model.User;
import ru.job4j.crud.model.UserStore;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Servlet for displaying users by login.
 *
 * @since 09/02/2018
 * @version 3
 */
public class DisplayServlet extends HttpServlet {

    private static final Logger LOG = LoggerFactory.getLogger(DisplayServlet.class);
    private final UserStore store = UserStore.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> users = store.getAllUsers();
        req.setAttribute("users", users);
        req.getRequestDispatcher("/WEB-INF/views/DisplayUser.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        req.setAttribute("user", store.getUser(login));
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/DisplayUser.jsp");
        dispatcher.forward(req, resp);
    }
}
