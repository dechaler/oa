package com.de.controller;

import com.de.Utils.DateUtils;
import com.de.Utils.JsonResultType;
import com.de.Utils.RequestParams;
import com.de.Utils.ResponseInfo;
import com.de.entity.Attendance;
import com.de.entity.Employee;
import com.de.service.AttendService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 * @编写人:de
 * @时间:2019/10/22
 * @描述:考勤模块控制器
 */
@Controller
@RequestMapping("/attendance")
public class AttendController {
    @Autowired
    private AttendService attendService;
    @Autowired
    private JsonResultType<Attendance> jsonResultType;
    private PageInfo<Attendance> pageInfo;


    @ResponseBody
    @RequestMapping(value = "/signIn",method = RequestMethod.POST)
    public int signIn(Attendance attendance, @SessionAttribute Employee employee, HttpServletResponse response) throws ParseException {
        attendance.setEmployee(employee);
        String signTime = DateUtils.dateToStrDateTime(new Date(), DateUtils.DATEFORMATWITHTIME);
        attendance.setSignTime(signTime);
        String attendTime = DateUtils.dateToStrDateTime(new Date(), DateUtils.DATEFORMATWITHDATE);
        attendance.setAttendDate(attendTime);
        int re = attendService.signIn(attendance);
        re = ResponseInfo.verifyDatas(response, re);
        return re;
    }

    @ResponseBody
    @RequestMapping("/selectAttendInfo")
    public JsonResultType<Attendance> selectAttendInfoByEmpIdAndDateScopeAndWay(@SessionAttribute Employee employee, RequestParams params,HttpServletResponse response){
        int page = params.getPage();
        int limit = params.getLimit();
        Integer id = employee.getId();
        String startDate = params.getStartDate();
        String endDate = params.getEndDate();
        if (startDate.startsWith("undefined") || endDate.startsWith("undefined")) {
            startDate = "";
            endDate = "";
        }
        Integer kqWay = params.getKqWay();
        PageHelper.startPage(page,limit);
        List<Attendance> attendances = attendService.selectAttendInfoByEmpIdAndDateScopeAndWay(id, startDate, endDate, kqWay);
        pageInfo = new PageInfo<>(attendances);
        jsonResultType = ResponseInfo.verifyDatas(response,jsonResultType,pageInfo);
        return jsonResultType;
    }


    @ResponseBody
    @RequestMapping("/selectAllAttendInfo")
    public JsonResultType<Attendance> selectAttendInfoByEmpId(@SessionAttribute Employee employee, RequestParams params,HttpServletResponse response){
        int page = params.getPage();
        int limit = params.getLimit();
        Integer id = employee.getId();
        PageHelper.startPage(page,limit);
        List<Attendance> attendances = attendService.selectAttendInfoByEmpId(id);
        pageInfo = new PageInfo<>(attendances);
        jsonResultType = ResponseInfo.verifyDatas(response,jsonResultType,pageInfo);
        return jsonResultType;
    }


    @ResponseBody
    @RequestMapping("/selectClockInInfo")
    public JsonResultType<Attendance> selectClockInInfo(@SessionAttribute Employee employee,HttpServletResponse response){
        Integer id = employee.getId();
        List<Attendance> attendances = attendService.selectClockInInfo(id);
        jsonResultType = ResponseInfo.verifyDatas(attendances,response,jsonResultType);
        return jsonResultType;
    }
}
