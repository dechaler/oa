package com.de.controller;

import com.de.entity.Job;
import com.de.service.JobService;
import com.de.utils.JsonResultType;
import com.de.utils.ResponseInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Description
 * @Date 2020/3/8
 * @Author
 * @Version
 **/
@Controller
@RequestMapping("/job")
public class JobController {

    @Autowired
    private JobService jobService;

    @Autowired
    private JsonResultType<Job> jsonResultType;

    @RequestMapping("/selectAllJob")
    @ResponseBody
    public JsonResultType<Job> selectAllJob(HttpServletResponse response){
        List<Job> jobs = jobService.selectAllJob();
        jsonResultType = ResponseInfo.verifyDatas(jobs,response,jsonResultType);
        return jsonResultType;
    }
}
