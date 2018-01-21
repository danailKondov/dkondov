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
 * Class for Servlet processing GET, POST etc.
 *
 * @since 17/01/2018
 * @version 1
 */

public class UsersServlet extends HttpServlet{

    private static final Logger log = LoggerFactory.getLogger(UsersServlet.class);
    private final UserStore store = UserStore.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        User user = store.getUser(login);
        PrintWriter writer = resp.getWriter();

        // только для учебного примера!
        resp.setContentType("text/html");
        writer.append("<!DOCTYPE html>");
        writer.append("<html>");
        writer.append("<head>");
        writer.append("<title> User's info </title>");
        writer.append("</head>");
        writer.append("<body>");
        writer.append("<p> User with login " + login + " is: </p><br>");
        if (user != null) {
            writer.append("<p>" + user.toString() + "</p>");
        } else {
            writer.append("<p> There is no such user! </p>");
        }
        writer.append("</body>");
        writer.append("</html>");
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String email = req.getParameter("email");
        User user = new User(name, login, email);
        store.add(user);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String oldLogin = req.getParameter("oldLogin");
        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String email = req.getParameter("email");
        User user = new User(name, login, email);
        store.update(oldLogin, user);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        store.delete(login);
    }
}
