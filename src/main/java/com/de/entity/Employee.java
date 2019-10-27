package com.de.entity;

import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @编写人:de
 * @时间:2019/9/2
 * @描述:员工类
 */
@Component
public class Employee implements Serializable {
    private Integer id;
    private String name;
    private String password;
    private String sex;
    private Integer age;
    private Long phone;
    private String inerDate;
    private Department department;
    private Job job;

    public Employee() {
    }

    public Employee(Integer id, String name, String password, String sex, Integer age, Long phone, String inerDate, Department department, Job job) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.sex = sex;
        this.age = age;
        this.phone = phone;
        this.inerDate = inerDate;
        this.department = department;
        this.job = job;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public Employee(Integer id) {
        this.id = id;
    }

    public Employee(String name) {
        this.name = name;
    }

    public Employee(Integer id, String password) {
        this.id = id;
        this.password = password;
    }


    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }


    public String getInerDate() {

        return inerDate;
    }

    public void setInerDate(String inerDate) {
        this.inerDate = inerDate;
    }


    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", sex='" + sex + '\'' +
                ", age=" + age +
                ", phone=" + phone +
                ", job='" + job.getId() + '\'' +
                ", inerDate='" + inerDate + '\'' +
     //           ", depart_id='" + department.getId() + '\'' +
       //         ", depart_name='" + department.getName() + '\'' +
                '}';
    }
}

