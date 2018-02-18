package ru.job4j.crud.servlets;

import org.junit.Test;
import org.mockito.Mockito;
import ru.job4j.crud.model.User;
import ru.job4j.crud.model.UserStore;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;

/**
 * Class for create user servlet test.
 *
 * @since 15/02/2018
 * @version 1
 */
public class CreateServletTest {

    @Test
    public void createUserTest() throws ServletException, IOException {
        CreateServlet servlet = new CreateServlet();
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = Mockito.mock(RequestDispatcher.class);

        Mockito.when(request.getParameter("name")).thenReturn("mockName");
        Mockito.when(request.getParameter("login")).thenReturn("mockLogin");
        Mockito.when(request.getParameter("password")).thenReturn("mockPass");
        Mockito.when(request.getParameter("role")).thenReturn("user");
        Mockito.when(request.getParameter("city")).thenReturn("Moscow");
        Mockito.when(request.getParameter("country")).thenReturn("Russia");
        Mockito.when(request.getParameter("email")).thenReturn("mockMail@mail.com");
        Mockito.when(request.getRequestDispatcher("/WEB-INF/views/CreateUser.jsp")).thenReturn(dispatcher);

        servlet.doPost(request, response);
        User user = UserStore.getInstance().getUser("mockLogin");
        assertTrue(user != null &&
                user.getName().equals("mockName") &&
                user.getPassword().equals("mockPass") &&
                user.getRole().equals("user") &&
                user.getEmail().equals("mockMail@mail.com"));
    }
}