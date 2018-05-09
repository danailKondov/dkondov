package ru.job4j.todolist.model;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Task entity.
 *
 *
 create table todotasks (
 id serial primary key,
 description text,
 create_date timestamp,
 task_is_done boolean
 );
 *
 * @since 26/04/2018
 * @version 1
 */
public class TaskItem {

    private Long id;
    private String desc;
    private Timestamp created;
    private boolean done;

    public TaskItem(String desc, Timestamp created, boolean done) {
        this.desc = desc;
        this.created = created;
        this.done = done;
    }

    public TaskItem() {
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

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public boolean getDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    @Override
    public String toString() {
        return "TaskItem{" +
                "desc='" + desc + '\'' +
                ", created=" + created +
                ", done=" + done +
                '}';
    }
}
