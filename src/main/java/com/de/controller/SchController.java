package com.de.controller;

import com.de.Utils.JsonResultType;
import com.de.Utils.RequestParams;
import com.de.Utils.ResponseInfo;
import com.de.entity.Employee;
import com.de.entity.Schedule;
import com.de.service.SchService;
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
 * @时间:2019/10/21
 * @描述:日程控制器
 */
@Controller
@RequestMapping("/sch")
public class SchController {
    @Autowired
    private SchService schService;
    @Autowired
    private JsonResultType<Schedule> jsonResultType;

    private PageInfo<Schedule> pageInfo;

    @RequestMapping("/selectSch")
    @ResponseBody
    public JsonResultType<Schedule> selectSchByDepartIdAndDate(RequestParams params, HttpServletResponse response ,@SessionAttribute Employee employee) {
        Integer departId = employee.getDepartment().getId();
//        Integer departId = params.getDepartId();
        String date = params.getDate();
        int page = params.getPage();
        int limit = params.getLimit();
        PageHelper.startPage(page,limit);
        List<Schedule> schedules = schService.selectSchByDepartIdAndDate(departId, date);
        pageInfo = new PageInfo<>(schedules);
        jsonResultType = ResponseInfo.verifyDatas(response,jsonResultType,pageInfo);
        return jsonResultType;
    }

    @RequestMapping("/addSch")
    @ResponseBody
    public int addSch(Schedule schedule,HttpServletResponse response) {
        int re = schService.addSch(schedule);
        return ResponseInfo.verifyDatas(response,re);
    }

    @RequestMapping("/delSchById")
    @ResponseBody
    public int delSchById(Integer schId,HttpServletResponse response) {
        int re = schService.delSchById(schId);
        return ResponseInfo.verifyDatas(response,re);
    }
}
