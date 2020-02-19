package com.de.controller;

import com.de.utils.JsonResultType;
import com.de.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @编写人:de
 * @时间:2019/10/25
 * @描述:
 */
@Controller
public class RequiredSessionController {

    @Autowired
    private JsonResultType<Employee> jsonResultType;


    @ResponseBody
    @RequestMapping(value = "/RequiredSession",method = RequestMethod.POST)
    public JsonResultType<Employee> RequiredSession(HttpServletRequest request) {
        Employee employee = (Employee)request.getSession().getAttribute("employee");
        if (null != employee) {
            List<Employee> list = new ArrayList<>();
            list.add(employee);
            jsonResultType.setCode("" + 0);
            jsonResultType.setMsg("");
            jsonResultType.setCount("" + list.size());
            jsonResultType.setData(list);
            return jsonResultType;
        }else {
            jsonResultType.setCode("" + -1);
            jsonResultType.setMsg("");
            jsonResultType.setCount("" + 0);
            jsonResultType.setData(new ArrayList<>());
            return jsonResultType;
        }

    }
}
