package com.de.controller;

import com.de.Utils.JsonResultType;
import com.de.Utils.RequestParams;
import com.de.Utils.ResponseInfo;
import com.de.entity.Department;
import com.de.service.DepartService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @编写人:de
 * @时间:2019/10/21
 * @描述:部门控制器
 */
@Controller
@RequestMapping("/depart")
public class DepartController {

    @Autowired
    private JsonResultType<Department> jsonResultType;

    @Autowired
    private DepartService departService;

    private PageInfo<Department> pageInfo;

    @ResponseBody
    @RequestMapping("/selectDepartInfo")
    public JsonResultType<Department> selectDepartInfo(RequestParams params , HttpServletResponse response) {
        int page = params.getPage();
        int limit = params.getLimit();
        PageHelper.startPage(page,limit);
        List<Department> departments = departService.selectAllDepart();
        pageInfo = new PageInfo<>(departments);
        jsonResultType = ResponseInfo.verifyDatas(response,jsonResultType,pageInfo);
        return jsonResultType;

    }
}
