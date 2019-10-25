package com.de.entity;

/**
 * @编写人:de
 * @时间:2019/9/2
 * @描述:请假类
 */
public class Leave {
    private Integer id;
    private String reason;
    private String startTime;
    private String endTime;
    //请假审核状态
    private int status;
    private Employee employee;

    public Leave() {
    }

    public Leave(String reason, String startTime, String endTime) {
        this.reason = reason;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Leave(Integer id, String reason, String startTime, String endTime, int status, Employee employee) {
        this.id = id;
        this.reason = reason;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
        this.employee = employee;
    }

    public Leave(Integer id, String reason, String startTime, String endTime) {
        this.id = id;
        this.reason = reason;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    public String toString() {
        return "Leave{" +
                "id=" + id +
                ", reason='" + reason + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", status=" + status +
                ", employee=" + employee.getId() +
                '}';
    }
}
