package com.de.entity.vo;

/**
 * @Description
 * @Date 2020/2/20
 * @Author
 * @Version
 **/
public class EmployeeVo {
    private Integer id;
    private String name;
    private String password;
    private String sex;
    private Integer age;
    private Long phone;
    private String inerDate;
    private String department;
    private String job;

    public EmployeeVo() {
    }

    public EmployeeVo(Integer id, String name, String password, String sex, Integer age, Long phone, String inerDate, String department, String job) {
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

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }
}
