package com.pathfinder.anup.bean;

/**
 * Created by Anup on 6/27/2017.
 */

public class NewTodoBean {
    private int id;
    private String todoTitle;
    private String todoDesc;
    private String markDate;
    private String markTime;

    public NewTodoBean() {
    }

    public NewTodoBean(String todoTitle, String todoDesc, String markDate, String markTime) {
        this.todoTitle = todoTitle;
        this.todoDesc = todoDesc;
        this.markDate = markDate;
        this.markTime = markTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTodoTitle() {
        return todoTitle;
    }

    public void setTodoTitle(String todoTitle) {
        this.todoTitle = todoTitle;
    }

    public String getTodoDesc() {
        return todoDesc;
    }

    public void setTodoDesc(String todoDesc) {
        this.todoDesc = todoDesc;
    }

    public String getMarkDate() {
        return markDate;
    }

    public void setMarkDate(String markDate) {
        this.markDate = markDate;
    }

    public String getMarkTime() {
        return markTime;
    }

    public void setMarkTime(String markTime) {
        this.markTime = markTime;
    }
}
