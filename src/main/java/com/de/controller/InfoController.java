package com.de.controller;

import com.de.entity.Employee;
import com.de.service.EmpService;
import com.de.utils.JsonResultType;
import com.de.utils.ResponseInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description 修改信息
 * @Date 2020/2/16
 * @Author
 * @Version
 **/
@Controller
@RequestMapping("/info")
public class InfoController {

    @Autowired
    private EmpService empService;

    @Autowired
    private JsonResultType jsonResultType;

    @ResponseBody
    @PostMapping("/selectInfo")
    public JsonResultType selectInfo(@SessionAttribute Employee employee, HttpServletResponse response){
        Integer empId = employee.getId();
        Employee emp = empService.selectEmpById(empId);
        List<Employee> temp = new ArrayList<>();
        temp.add(emp);
        JsonResultType jsonResultType = ResponseInfo.verifyDatas(temp, response, this.jsonResultType);
        return jsonResultType;
    }
}
