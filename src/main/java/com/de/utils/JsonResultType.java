package com.de.utils;

import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @编写人:de
 * @时间:2019/9/5
 * @描述:返回layui所用到的json格式的数据
 */


@Component
public class JsonResultType<T> {

//    规定数据状态的字段名称
    private String status;

//    规定成功的状态码，默认：0
    private String code = "0";

//    规定状态信息的字段名称，默认：msg
    private String msg = "";

//    规定数据总数的字段名称，默认：count
    private String count;

// 规定数据列表的字段名称，默认：data
    private List<T> data;

    public JsonResultType() {
    }

    public JsonResultType(String status, String code, String msg, String count, List<T> data) {
        this.status = status;
        this.code = code;
        this.msg = msg;
        this.count = count;
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

//    @Override
//    public String toString() {
//        return "JsonResultType{" +
//                "status='" + status + '\'' +
//                ", code='" + code + '\'' +
//                ", msg='" + msg + '\'' +
//                ", count='" + count + '\'' +
//                ", data=" + data +
//                '}';
//    }
}
