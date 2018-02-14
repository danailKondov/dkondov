package ru.job4j.crud.servlets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.crud.model.Role;
import ru.job4j.crud.model.User;
import ru.job4j.crud.model.UserStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Servlet for update user.
 *
 * @since 09/02/2018
 * @version 3
 */
public class UpdateServlet extends HttpServlet {

    private static final Logger LOG = LoggerFactory.getLogger(UpdateServlet.class);
    private final UserStore store = UserStore.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> users = store.getAllUsers();
        List<Role> roles = store.getAllRoles();
        req.setAttribute("users", users);
        req.setAttribute("roles", roles);
//        req.setAttribute("oldLogin", req.getAttribute("oldLogin"));
        req.getRequestDispatcher("/WEB-INF/views/UpdateUser.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String oldLogin = req.getParameter("oldLogin");
        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String role = req.getParameter("role");
        String email = req.getParameter("email");
        User user = new User(name, login, password, role, email);
        store.update(oldLogin, user);
        List<User> users = store.getAllUsers();
        List<Role> roles = store.getAllRoles();
        req.setAttribute("users", users);
        req.setAttribute("roles", roles);
        req.getRequestDispatcher("/WEB-INF/views/UpdateUser.jsp").forward(req, resp);
    }
}
