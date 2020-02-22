package com.de.controller;

import com.de.entity.Department;
import com.de.entity.Employee;
import com.de.entity.Job;
import com.de.entity.vo.EmployeeVo;
import com.de.service.EmpService;
import com.de.utils.JsonResultType;
import com.de.utils.ResponseInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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

    @ResponseBody
    @PostMapping("/repassword")
    public int repassword(@SessionAttribute Employee employee, String password, HttpServletResponse response, HttpSession session){
        Integer id = employee.getId();
        int re = empService.updatePwdById(id, password);
        re = ResponseInfo.verifyDatas(response,re);
        if (re > 0) {
            session.invalidate();
        }
        return re;
    }



    @ResponseBody
    @PostMapping("/update")
    public int update(EmployeeVo employeeVo, HttpServletResponse response){
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeVo,employee);
        String departName = employeeVo.getDepartment();
        String jobName = employeeVo.getJob();
        Job job = new Job();
        job.setName(jobName);
        employee.setJob(job);
        Department department = new Department();
        department.setName(departName);
        employee.setDepartment(department);
        int re = empService.updateInfoById(employee.getId(),employee);
        int i = ResponseInfo.verifyDatas(response, re);
        return i;
    }
}
