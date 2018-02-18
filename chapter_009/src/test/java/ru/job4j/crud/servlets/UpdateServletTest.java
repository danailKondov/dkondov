package ru.job4j.crud.servlets;

import org.junit.Test;
import org.mockito.Mockito;
import ru.job4j.crud.model.User;
import ru.job4j.crud.model.UserStore;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * Class for update user servlet test.
 *
 * @since 18/02/2018
 * @version 2
 */
public class UpdateServletTest {

    @Test
    public void updateUserTest() throws ServletException, IOException {
        UpdateServlet servlet = new UpdateServlet();
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = Mockito.mock(RequestDispatcher.class);

        Mockito.when(request.getParameter("name")).thenReturn("mockName");
        Mockito.when(request.getParameter("login")).thenReturn("mockLogin");
        Mockito.when(request.getParameter("password")).thenReturn("mockPass");
        Mockito.when(request.getParameter("role")).thenReturn("user");
        Mockito.when(request.getParameter("email")).thenReturn("mockMail@mail.com");
        Mockito.when(request.getParameter("city")).thenReturn("Paris");
        Mockito.when(request.getParameter("country")).thenReturn("France");
        Mockito.when(request.getParameter("oldLogin")).thenReturn("oldMockLogin");
        Mockito.when(request.getRequestDispatcher("/WEB-INF/views/UpdateUser.jsp")).thenReturn(dispatcher);

        User user = new User("deleteTestName", "oldMockLogin", "deletePass", "user", "email@delete.com", "Moscow", "Russia");
        UserStore.getInstance().add(user);

        servlet.doPost(request, response);

        assertNull(UserStore.getInstance().getUser("oldMockLogin"));
        assertNotNull(UserStore.getInstance().getUser("mockLogin"));
    }
}