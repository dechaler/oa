package com.de.entity;

import org.springframework.stereotype.Component;

/**
 * @编写人:de
 * @时间:2019/9/2
 * @描述:出勤类
 */
@Component
public class Attendance {
    private Integer id;

    //考勤日期
    private String attendDate;
//    出勤时间
    private String signTime;
//    考勤方式
    private int way;
//    考勤标识
    private Integer flag;
    private Employee employee;

    public Attendance() {
    }

    public Attendance(String signTime, int way, Integer flag) {
        this.signTime = signTime;
        this.way = way;
        this.flag = flag;
    }

    public String getAttendDate() {
        return attendDate;
    }

    public void setAttendDate(String attendDate) {
        this.attendDate = attendDate;
    }

    public Attendance(Integer id, String attendDate, String signTime, int way, Integer flag, Employee employee) {
        this.id = id;
        this.attendDate = attendDate;
        this.signTime = signTime;
        this.way = way;
        this.flag = flag;
        this.employee = employee;
    }

    public Attendance(Integer id, String signTime, int way, Integer flag) {
        this.id = id;
        this.signTime = signTime;
        this.way = way;
        this.flag = flag;
    }

    public String getSignTime() {
        return signTime;
    }

    public void setSignTime(String signTime) {
        this.signTime = signTime;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }



    public int getWay() {
        return way;
    }

    public void setWay(int way) {
        this.way = way;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    @Override
    public String toString() {
        return "Attendance{" +
                "id=" + id +
                ", attendDate='" + attendDate + '\'' +
                ", signTime='" + signTime + '\'' +
                ", way='" + way + '\'' +
                ", flag='" + flag + '\'' +
                ", employee=" + employee.getId() +
                ", department=" + employee.getDepartment().getName() +
                '}';
    }
}
