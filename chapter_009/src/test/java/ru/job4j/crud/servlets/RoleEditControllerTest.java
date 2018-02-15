package ru.job4j.crud.servlets;

import org.junit.Test;
import org.mockito.Mockito;
import ru.job4j.crud.model.Role;
import ru.job4j.crud.model.UserStore;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * Class for role edit user servlet test.
 *
 * @since 15/02/2018
 * @version 1
 */
public class RoleEditControllerTest {

    @Test
    public void createRoleTest() throws ServletException, IOException {
        RoleEditController controller = new RoleEditController();
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = Mockito.mock(RequestDispatcher.class);

        Mockito.when(request.getParameter("action")).thenReturn("create");
        Mockito.when(request.getParameter("roleName")).thenReturn("testRole");
        Mockito.when(request.getRequestDispatcher("/WEB-INF/views/RoleEdit.jsp")).thenReturn(dispatcher);

        controller.doPost(request, response);

        boolean result = false;
        for (Role role : UserStore.getInstance().getAllRoles()) {
            if (role.getRole().equals("testRole")) {
                result = true;
                break;
            }
        }

        assertTrue(result);
    }

    @Test
    public void deleteRoleTest() throws ServletException, IOException {
        RoleEditController controller = new RoleEditController();
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = Mockito.mock(RequestDispatcher.class);

        Mockito.when(request.getParameter("action")).thenReturn("delete");
        Mockito.when(request.getParameter("roleName")).thenReturn("testDeleteRole");
        Mockito.when(request.getRequestDispatcher("/WEB-INF/views/RoleEdit.jsp")).thenReturn(dispatcher);
        UserStore.getInstance().addRole(new Role("testDeleteRole"));

        controller.doPost(request, response);

        boolean result = false;
        for (Role role : UserStore.getInstance().getAllRoles()) {
            if (role.getRole().equals("testDeleteRole")) {
                result = true;
                break;
            }
        }

        assertFalse(result);
    }
}