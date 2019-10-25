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
    private String job;
    private String inerDate;
    private Department department;

    //验证码
    private String verifyCode;

    public Employee() {
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

    public Employee(Integer id, String name, String password, String sex, Integer age, Long phone, String job, String inerDate, Department department) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.sex = sex;
        this.age = age;
        this.phone = phone;
        this.job = job;
        this.inerDate = inerDate;
        this.department = department;
    }

    public Employee(String name, String password, String sex, Integer age, Long phone, String job, String inerDate) {

        this.name = name;
        this.password = password;
        this.sex = sex;
        this.age = age;
        this.phone = phone;
        this.job = job;
        this.inerDate = inerDate;
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

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }


    public String getInerDate() {

        return inerDate;
    }

    public void setInerDate(String inerDate) {
        this.inerDate = inerDate;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
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
                ", job='" + job + '\'' +
                ", inerDate='" + inerDate + '\'' +
     //           ", depart_id='" + department.getId() + '\'' +
       //         ", depart_name='" + department.getName() + '\'' +
                '}';
    }
}

