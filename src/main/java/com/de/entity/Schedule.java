package com.de.entity;

import org.springframework.stereotype.Component;

/**
 * @编写人:de
 * @时间:2019/9/2
 * @描述:日程表(只有部门经理才能发布日程)
 */
@Component("Schedule")
public class Schedule {
    private Integer id;
//    日程任务
    private String schTask;
//    发布时间
    private String startTime;

    private Employee employee;

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Schedule() {
    }

    public Schedule(String schTask, String startTime) {
        this.schTask = schTask;
        this.startTime = startTime;
    }

    public Schedule(Integer id, String schTask, String startTime) {
        this.id = id;
        this.schTask = schTask;
        this.startTime = startTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSchTask() {
        return schTask;
    }

    public void setSchTask(String schTask) {
        this.schTask = schTask;
    }

    public String getstartTime() {
        return startTime;
    }

    public void setstartTime(String startTime) {
        this.startTime = startTime;
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "id=" + id +
                ", schTask='" + schTask + '\'' +
                ", startTime='" + startTime + '\'' +
                ", employee=" + employee.getName() +
                ", department=" + employee.getDepartment().getName() +
                '}';
    }
}
