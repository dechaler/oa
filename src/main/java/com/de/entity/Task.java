package com.de.entity;

import org.springframework.stereotype.Component;

/**
 * @编写人:de
 * @时间:2019/9/2
 * @描述:任务类
 */
@Component("Task")
public class Task {
    private Integer id;
    private String content;
    private String startTime;
    private String endTime;
    private Employee employee;

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Task() {
    }

    public Task(String content, String startTime, String endTime) {
        this.content = content;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Task(Integer id, String content, String startTime, String endTime) {
        this.id = id;
        this.content = content;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", employee=" + employee.getId() +
                '}';
    }
}
