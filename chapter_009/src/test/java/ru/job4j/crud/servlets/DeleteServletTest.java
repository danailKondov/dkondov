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
 * Class for delete user servlet test.
 *
 * @since 15/02/2018
 * @version 1
 */
public class DeleteServletTest {

    @Test
    public void deleteUserTest() throws ServletException, IOException {
        DeleteServlet servlet = new DeleteServlet();
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = Mockito.mock(RequestDispatcher.class);

        User user = new User("deleteTestName", "deleteTestLogin", "deletePass", "user", "email@delete.com");
        UserStore.getInstance().add(user);

        Mockito.when(request.getParameter("login")).thenReturn("deleteTestLogin");
        Mockito.when(request.getRequestDispatcher("/WEB-INF/views/DeleteUser.jsp")).thenReturn(dispatcher);

        servlet.doPost(request, response);

        assertNull(UserStore.getInstance().getUser("deleteTestLogin"));
    }


}