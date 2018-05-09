package ru.job4j.todolist.view;

import ru.job4j.todolist.model.TaskItem;

/**
 * Task to send.
 *
 * @since 08/05/2018
 * @version 1
 */
public class TaskItemDto {

    private Long id;
    private String desc;
    private String created;
    private boolean done;

    public TaskItemDto(TaskItem item) {
        id = item.getId();
        desc = item.getDesc();
        if (item.getCreated() != null) {
            created = item.getCreated().toString();
        } else {
            created = "no data";
        }
        done = item.getDone();
    }

    public TaskItemDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}
