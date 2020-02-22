package com.de.controller;

import com.de.utils.JsonResultType;
import com.de.utils.RequestParams;
import com.de.utils.ResponseInfo;
import com.de.entity.Employee;
import com.de.service.EmpService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.code.kaptcha.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * @编写人:de
 * @时间:2019/9/4
 * @描述:员工类的控制层
 */
@Controller
@RequestMapping("/emp")
public class EmpController {
    @Autowired
    private EmpService empService;

    @Autowired
    private JsonResultType<Employee> jsonResultType;
    private PageInfo<Employee> pageInfo;



    @ResponseBody
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public JsonResultType<Employee> login(Employee employee, String verifyCode,HttpSession session,HttpServletResponse response){
        String code = (String)session.getAttribute(Constants.KAPTCHA_SESSION_KEY);
        Integer id = employee.getId();
        String password = employee.getPassword();
       //先验证码验证信息
        if (code.equals(verifyCode)) {
            int re = empService.login(id, password);
            if (re > 0) {
                employee = empService.selectEmpById(id);
                session.setAttribute("employee", employee);
                List<Employee> list = new ArrayList<>();
                boolean flag = list.add(employee);
                if (flag) {
                    jsonResultType.setCode("" + 0);
                    jsonResultType.setCount("" + list.size());
                    jsonResultType.setData(list);
                    return jsonResultType;
                } else {
                    jsonResultType.setCode("" + -1);
                    jsonResultType.setData(new ArrayList<>());
                    return jsonResultType;
                }
            } else {
                jsonResultType.setCode("" + -1);
                jsonResultType.setData(new ArrayList<>());
                return jsonResultType;
            }
        }else {
            jsonResultType.setCode("" + -2);
            List<Employee> list = new ArrayList<>();
            jsonResultType.setData(list);
            return jsonResultType;
        }
    }

    @RequestMapping(value = "/selectAllEmp")
    @ResponseBody
    public JsonResultType<Employee> selectAllEmp(RequestParams params, HttpServletResponse response) {
        /**
         *@描述信息：以json格式返回员工数据
         *
         * @参数： [page, limit, responce]
         * @返回值：com.de.Utils.JsonResultType<com.de.entity.Employee>
         * @编写人：de
         * @时间： 2019/9/5
         */
        Integer empId = params.getEmpId();
        String name = params.getName();
        String departName = params.getDepartName();
        int limit = params.getLimit();
        int page = params.getPage();
        PageHelper.startPage(page, limit);
        List<Employee> employees = empService.selectEmpByIdOrNameOrDepartName(empId, name, departName);
        pageInfo = new PageInfo<>(employees);
        //("" +  ) 返回String
        jsonResultType = ResponseInfo.verifyDatas(response,jsonResultType,pageInfo);
        return jsonResultType;
    }

    @RequestMapping("/updateEmpById")
    @ResponseBody
    public int updateEmpById(Integer empId, Employee employee, HttpSession session){
        Employee emp = (Employee)session.getAttribute("employee");
        System.out.println(emp.getAge());
        int re = empService.updateEmpById(empId, employee);
        Employee emp1 = (Employee)session.getAttribute("employee");
        System.out.println(emp1.getAge());
        if (re > 0) {
            return re;
        }
        return re;
    }

    @RequestMapping("/selectEmpById")
    @ResponseBody
    public JsonResultType selectEmpById(Employee employee, HttpSession session,HttpServletResponse response){
        Employee emp = (Employee)session.getAttribute("employee");
        Integer id = emp.getId();
        Employee temp = empService.selectEmpById(id);
        JsonResultType jsonResultType = ResponseInfo.verifyDatas(temp, response, this.jsonResultType);
        return jsonResultType;

    }

    @RequestMapping("/logout")
    @ResponseBody
    public int logout(HttpSession session) {
        session.invalidate();
        return 1;
    }

}





