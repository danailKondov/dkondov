package ru.job4j.crud.servlets;

import ru.job4j.crud.model.Role;
import ru.job4j.crud.model.UserStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Servlet for edit roles.
 *
 * @since 14/02/2018
 * @version 1
 */
public class RoleEditController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getSession().getAttribute("role").equals("admin")) {
            List<Role> roles = UserStore.getInstance().getAllRoles();
            req.setAttribute("roles", roles);
        }
        req.getRequestDispatcher("/WEB-INF/views/RoleEdit.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        String roleName = req.getParameter("roleName");
        if (action != null && action.equals("create")) {
            UserStore.getInstance().addRole(new Role(roleName));
        } else if (action != null && action.equals("delete")) {
            UserStore.getInstance().deleteRole(roleName);
        }
        List<Role> roles = UserStore.getInstance().getAllRoles();
        req.setAttribute("roles", roles);
        req.getRequestDispatcher("/WEB-INF/views/RoleEdit.jsp").forward(req, resp);
    }
}
