package com.de.entity;

/**
 * @编写人:de
 * @时间:2019/9/2
 * @描述:
 */
public class File {
    private Integer id;
    private String fileName;
    //    文件上传时间
    private String upTime;
    private String filePath;
    private Employee employee;

    public File() {
    }

    public File(String fileName, String upTime) {
        this.fileName = fileName;
        this.upTime = upTime;
    }

    public File(Integer id, String fileName, String upTime) {
        this.id = id;
        this.fileName = fileName;
        this.upTime = upTime;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getUpTime() {
        return upTime;
    }

    public void setUpTime(String upTime) {
        this.upTime = upTime;
    }

    @Override
    public String toString() {
        return "File{" +
                "id=" + id +
                ", fileName='" + fileName + '\'' +
                ", upTime='" + upTime + '\'' +
                ", filePath='" + filePath + '\'' +
                ", employee=" + employee.getName() +
                '}';
    }
}