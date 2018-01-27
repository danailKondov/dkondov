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
 * Servlet for update user.
 *
 * @since 26/01/2018
 * @version 1
 */
public class UpdateServlet extends HttpServlet {

    private static final Logger LOG = LoggerFactory.getLogger(UpdateServlet.class);
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
                "<table align='centre' bordercolor='#008000' border='2'>" +
                "<tr><td> Name </td><td> Login </td><td> E-mail </td><td> Data </td><td> Update </td><td> Delete </td></tr>"
        );
        for (User user : users) {
            writer.print("<tr>" +
                    "<td>" + user.getName() + "</td>" +
                    "<td>" + user.getLogin() + "</td>" +
                    "<td>" + user.getEmail() + "</td>" +
                    "<td>" + user.getCreateDate().toString() + "</td>" +
                    "<td><form action='" + req.getContextPath() + "/update' method='GET'>" +
                    "<input type='submit' value='Update'>" +
                    "<input type='hidden' name='oldLogin' value='"+ user.getLogin() +"'>" +
                    "</form></td>" +
                    "<td><form action='" + req.getContextPath() + "/delete' method='POST'>" +
                    "<input type='submit' value='Delete'>" +
                    "<input type='hidden' name='login' value='"+ user.getLogin() +"'>" +
                    "</form></td>" +
                    "</tr>");
        }
        writer.print("</table><br><br>" +
                "<h2> Update user - enter new data: </h2>" +
                "<form action='" + req.getContextPath() + "/update' method='POST'>" +
                "Name: <input type='text' name='name' value='new name'><br>" +
                "Login: <input type='text' name='login' value='new login'><br>" +
                "E-mail: <input type='text' name='email' value='newemail@email.com'><br><br>" +
                "<input type='hidden' name='oldLogin' value='"+ req.getParameter("oldLogin") +"'>" +
                "<input type='submit' value='Submit'>" +
                "</form>" +
                "</body>" +
                "</html>");
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String oldLogin = req.getParameter("oldLogin");
        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String email = req.getParameter("email");
        User user = new User(name, login, email);
        store.update(oldLogin, user);
        doGet(req, resp);
    }
}
