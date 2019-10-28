package com.de.controller;

import com.de.Utils.JsonResultType;
import com.de.Utils.RequestParams;
import com.de.Utils.ResponseInfo;
import com.de.entity.Employee;
import com.de.service.EmpService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.code.kaptcha.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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
    @RequestMapping("/login")
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
//        response.setContentType("application/json; charset=utf-8");
//        System.out.println("page:  " + page + " limit:  " + limit);
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


    @RequestMapping("/selectEmpByIdOrNameOrDepartId")
    @ResponseBody
    public JsonResultType<Employee> selectEmpByIdOrNameOrDepartName(RequestParams params, HttpServletResponse response) {
        Integer empId = params.getEmpId();
        String name = params.getName();
        String departName = params.getDepartName();
        int page = params.getPage();
        int limit = params.getLimit();
        PageHelper.startPage(page,limit);
        List<Employee> employees = empService.selectEmpByIdOrNameOrDepartName(empId, name, departName);
        pageInfo = new PageInfo<>(employees);
        jsonResultType = ResponseInfo.verifyDatas(response,jsonResultType,pageInfo);
        return jsonResultType;
    }


//    @RequestMapping("/selectEmp")
//    @ResponseBody
//    public JsonResultType<Employee> selectEmp(RequestParams params, HttpServletResponse response){
//        Integer empId = params.getEmpId();
//        Integer departId = params.getDepartId();
//        String name = params.getName();
//        int page = params.getPage();
//        int limit = params.getLimit();
//        if (null == empId  && null == departId && null == name) {
//
//            List<Employee> employees = empService.selectAllEmp();
//        }
//    }
    @RequestMapping("/logout")
    @ResponseBody
    public int logout(HttpSession session) {
        session.invalidate();
        return 1;
    }

}





