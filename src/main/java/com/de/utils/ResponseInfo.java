package com.de.utils;

import com.github.pagehelper.PageInfo;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @编写人:de
 * @时间:2019/10/21
 * @描述:封装返回信息类
 */
public class ResponseInfo {
//    private HttpServletResponse response;
//    private JsonResultType<T> jsonResultType;
//    private PageInfo<T> pageInfo;
//
//    public ResponseInfo(HttpServletResponse response, JsonResultType<T> jsonResultType) {
//        this.response = response;
//        this.jsonResultType = jsonResultType;
//    }

    public static <T> JsonResultType<T> verifyDatas(HttpServletResponse response,JsonResultType<T> jsonResultType,PageInfo<T> pageInfo){
        if (response.getStatus() == HttpServletResponse.SC_OK) {
            jsonResultType.setCode("" + 0);
            jsonResultType.setCount("" + pageInfo.getTotal());
            jsonResultType.setData(pageInfo.getList());
            return jsonResultType;
        } else {
            jsonResultType.setCode("" + 0);
            jsonResultType.setMsg("数据为空");
            jsonResultType.setData(null);
            return jsonResultType;
        }
    }


    public static <T> JsonResultType<T> verifyDatas(List<T> datas, HttpServletResponse response, JsonResultType<T> jsonResultType){
        if (response.getStatus() == HttpServletResponse.SC_OK) {
            jsonResultType.setCode("" + 0);
            jsonResultType.setCount("" + datas.size());
            jsonResultType.setData(datas);
            return jsonResultType;
        } else {
            jsonResultType.setCode("" + 0);
            jsonResultType.setMsg("数据为空");
            jsonResultType.setData(null);
            return jsonResultType;
        }
    }

    public static int verifyDatas(HttpServletResponse response,int result){
        if (response.getStatus() == HttpServletResponse.SC_OK) {
            return result;
        } else {
           return  -1;
        }
    }

    public static JsonResultType verifyDatas(Object data, HttpServletResponse response, JsonResultType jsonResultType){
        if (response.getStatus() == HttpServletResponse.SC_OK) {
            jsonResultType.setCode("" + 0);
            jsonResultType.setCount("" + 1);
            jsonResultType.setData(data);
            return jsonResultType;
        } else {
            jsonResultType.setCode("" + 0);
            jsonResultType.setMsg("数据为空");
            jsonResultType.setData(null);
            return jsonResultType;
        }
    }
}
