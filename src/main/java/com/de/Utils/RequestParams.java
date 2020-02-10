package com.de.Utils;

/**
 * @编写人:de
 * @时间:2019/10/20
 * @描述:通用的请求参数类
 */
public class RequestParams extends MyPageParams {
    //员工号
    private Integer empId;
    //员工名
    private String name;
    //部门号
    private Integer departId;

    //日期
    private String Date;

    //日期范围
    private String startDate;

    private String endDate;

    //flag（考勤）
    private Integer kqFlag;

    //way（考勤）
    private Integer kqWay;

    private String departName;

    private String fileName;


    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Integer getEmpId() {

        return empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public Integer getDepartId() {
        return departId;
    }

    public void setDepartId(Integer departId) {
        this.departId = departId;
    }

    public String getStartDate() {

        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Integer getKqFlag() {
        return kqFlag;
    }

    public void setKqFlag(Integer kqFlag) {
        this.kqFlag = kqFlag;
    }

    public String getDepartName() {
        return departName;
    }

    public void setDepartName(String departName) {
        this.departName = departName;
    }

    public Integer getKqWay() {
        return kqWay;
    }

    public void setKqWay(Integer kqWay) {
        this.kqWay = kqWay;
    }
}
