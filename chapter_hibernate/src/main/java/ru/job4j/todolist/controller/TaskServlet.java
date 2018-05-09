package ru.job4j.todolist.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.todolist.dao.TaskItemDao;
import ru.job4j.todolist.model.TaskItem;
import ru.job4j.todolist.view.TaskItemDto;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Task servlet.
 *
 * @since 26/04/2018
 * @version 1
 */
public class TaskServlet extends HttpServlet {

    private static final Logger log = LoggerFactory.getLogger(TaskServlet.class);
    private TaskItemDao itemDao = TaskItemDao.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        resp.setContentType("text/json");
        resp.setCharacterEncoding("UTF-8");
        List<TaskItem> taskItems = itemDao.getAllItems();
        List<TaskItemDto> dataToSend = taskItems.stream().map(task -> new TaskItemDto(task)).collect(Collectors.toList());
        // Jackson mapper
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(writer, dataToSend);
        writer.flush();

//        resp.sendRedirect(req.getContextPath() + "/");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String description = req.getParameter("desc");
        String done = req.getParameter("isDone");
        boolean isDone = Boolean.parseBoolean(done);
        Timestamp created = Timestamp.from(Instant.now());
        TaskItem item = new TaskItem(description, created, isDone);
        itemDao.save(item);
    }

    @Override
    public void destroy() {
        log.info("closing DB...");
        itemDao.close();
        super.destroy();
    }
}
