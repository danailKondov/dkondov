package ru.job4j.crud.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.job4j.crud.model.City;
import ru.job4j.crud.model.UserStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Servlet for update cities by country.
 *
 * @since 18/02/2018
 * @version 1
 */
public class UpdateCitiesController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String country = req.getParameter("country");
        if (country != null && !country.isEmpty()) {
            PrintWriter writer = resp.getWriter();
            resp.setContentType("text/json");
            resp.setCharacterEncoding("UTF-8");

            List<City> cities = UserStore.getInstance().getAllCities();
            List<City> result = new ArrayList<>();
            for (int i = 0; i < cities.size(); i++) {
                if (country.equals(cities.get(i).getCountry())) {
                    result.add(cities.get(i));
                }
            }

            // Jackson mapper
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(writer, result);
            writer.flush();
        }
    }
}
