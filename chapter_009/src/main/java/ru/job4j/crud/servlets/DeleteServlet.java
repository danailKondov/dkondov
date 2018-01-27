package ru.job4j.crud.servlets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
 * Servlet for delete users.
 *
 * @since 26/01/2018
 * @version 1
 */
public class DeleteServlet extends HttpServlet{

    private static final Logger LOG = LoggerFactory.getLogger(DeleteServlet.class);
    private final UserStore store = UserStore.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> users = store.getAllUsers();
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();
        writer.print("<!DOCTYPE html>" +
                "<head>" +
                "<title>Users database</title>" +
                "</head>" +
                "<body>" +
                "<h1> Users database </h1><br>" +
                "<a href='" + req.getContextPath() + "/create'> Add new user </a><br><br>" +
                "<table align='centre' bordercolor='#008000' border='2'>" +
                "<tr><td> Name </td><td> Login </td><td> E-mail </td><td> Data </td><td> Update </td><td> Delete </td></tr>"
        );
        for (User user : users) {
            writer.print("<tr>" +
                    "<td>" + user.getName() + "</td>" +
                    "<td>" + user.getLogin() + "</td>" +
                    "<td>" + user.getEmail() + "</td>" +
                    "<td>" + user.getCreateDate().toString() + "</td>" +
                    // как передать параметр в POST-запросе с помощью кноки?
                    "<td><form action='" + req.getContextPath() + "/update' method='GET'>" +
                        "<input type='submit' value='Update'>" +
                        "<input type='hidden' name='oldLogin' value='"+ user.getLogin() +"'>" +
                    "</form></td>" +
                    "<td><form action='" + req.getContextPath() + "/delete' method='POST'>" +
                    "<input type='submit' value='Delete'>" +
                    "<input type='hidden' name='login' value='"+ user.getLogin() +"'>" +
                    "</form></td>" +
                    "<tr>");
        }
        writer.print("</table>" +
                "</body>" +
                "</html>");
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        store.delete(login);
        doGet(req, resp);
    }
}
