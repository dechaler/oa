package com.de.controller;

import com.de.utils.JsonResultType;
import com.de.utils.LeaveStatus;
import com.de.utils.RequestParams;
import com.de.utils.ResponseInfo;
import com.de.entity.Employee;
import com.de.entity.Leave;
import com.de.service.LeaveService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @编写人:de
 * @时间:2019/10/23
 * @描述:
 */
@Controller
@RequestMapping("/leave")
public class LeaveController {

    @Autowired
    private JsonResultType<Leave> jsonResultType;

    @Autowired
    private LeaveService leaveService;

    private PageInfo<Leave> pageInfo;


    @RequestMapping("/addLeave")
    @ResponseBody
    public int addLeave(Leave leave , @SessionAttribute Employee employee ,HttpServletResponse response){
        leave.setEmployee(employee);
        leave.setStatus(LeaveStatus.WAIT);
        int re = leaveService.addLeave(leave);
        re = ResponseInfo.verifyDatas(response,re);
        return re;
    }

    @RequestMapping("/selectLeaveInfo")
    @ResponseBody
    public JsonResultType<Leave> selectLeaveInfoByEmpId(RequestParams params, @SessionAttribute Employee employee, HttpServletResponse response) {
        int page = params.getPage();
        int limit = params.getLimit();
        Integer empId = employee.getId();
        PageHelper.startPage(page,limit);
        List<Leave> leaves = leaveService.selectLeaveInfoByEmpId(empId);
        pageInfo = new PageInfo<>(leaves);
        jsonResultType = ResponseInfo.verifyDatas(response,jsonResultType,pageInfo);
        return jsonResultType;
    }


    /**
     *@描述信息：人事管理查询全部请假信息（除人事部）
     *
     * @参数：
     * @返回值：
     * @编写人：de
     * @时间： 2020/3/11
     */

    @RequestMapping("/selectAllLeaveInfo")
    @ResponseBody
    public JsonResultType<Leave> selectAllLeaveInfo(RequestParams params, HttpServletResponse response) {
        int page = params.getPage();
        int limit = params.getLimit();
        PageHelper.startPage(page,limit);
        List<Leave> leaves = leaveService.selectLeaveAllInf();
        pageInfo = new PageInfo<>(leaves);
        jsonResultType = ResponseInfo.verifyDatas(response,jsonResultType,pageInfo);
        return jsonResultType;
    }

    @RequestMapping("/updateStatusById")
    @ResponseBody
    public int updateStatusById(Integer status,Integer id, HttpServletResponse response) {
        int re = leaveService.updateStatusById(status, id);
        return ResponseInfo.verifyDatas(response, re);
    }

}
