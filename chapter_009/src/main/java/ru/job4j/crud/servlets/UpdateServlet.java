package ru.job4j.crud.servlets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.crud.model.Country;
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
        addAllAttributesTo(req);
        req.getRequestDispatcher("/WEB-INF/views/UpdateUser.jsp").forward(req, resp);
    }

    private void addAllAttributesTo(HttpServletRequest req) {
        List<User> users = store.getAllUsers();
        List<Role> roles = store.getAllRoles();
        List<Country> countries = store.getAllCountries();
        req.setAttribute("users", users);
        req.setAttribute("roles", roles);
        req.setAttribute("countries", countries);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String oldLogin = req.getParameter("oldLogin");
        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String role = req.getParameter("role");
        String email = req.getParameter("email");
        String city = req.getParameter("city");
        String country = req.getParameter("country");

        User user = new User(name, login, password, role, email, city, country);
        store.update(oldLogin, user);
        addAllAttributesTo(req);
        req.getRequestDispatcher("/WEB-INF/views/UpdateUser.jsp").forward(req, resp);
    }
}
