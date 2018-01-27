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

/**
 * Servlet for displaying users by login.
 *
 * @since 26/01/2018
 * @version 1
 */
public class DisplayServlet extends HttpServlet {

    private static final Logger LOG = LoggerFactory.getLogger(DisplayServlet.class);
    private final UserStore store = UserStore.getInstance();
    private User user;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();
        writer.append("<!DOCTYPE html>" +
                "<head>" +
                   "<title>Users database</title>" +
                "</head>" +
                "<body>" +
                    "<p> Enter user's login: </p>" +
                    "<form action = '" + req.getContextPath() + "/display' method ='POST'>" +
                        "   Login: <input type='text' name='login'>" +
                        "<input type='submit' value='Submit'>" +
                    "</form>" +
                    "<br> User to display: ");
        if (user != null) {
            writer.append(user.toString());
        } else {
            writer.append("no user");
        }
        writer.append(
                "</body>" +
                "</html>");
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        user = store.getUser(login);
        doGet(req, resp);
    }
}
