package com.de.controller;
import com.de.Utils.JsonResultType;
import com.de.Utils.RequestParams;
import com.de.Utils.ResponseInfo;
import com.de.entity.Task;
import com.de.service.TaskService;
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
 * @描述:计划任务控制器
 */
@Controller
@RequestMapping("/Task")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @Autowired
    private JsonResultType<Task> jsonResultType;

    private PageInfo<Task> pageInfo;



    @ResponseBody
    @RequestMapping("/selectEmpTask")
    public JsonResultType<Task> selectEmpTask(RequestParams params, HttpServletResponse response) {
        int page = params.getPage();
        int limit = params.getLimit();
        Integer empId = params.getEmpId();
        PageHelper.startPage(page,limit);
       List<Task> tasks = taskService.selectEmpTaskByEmpId(empId);
       pageInfo = new PageInfo<>(tasks);
       this.jsonResultType = ResponseInfo.verifyDatas(response, jsonResultType, pageInfo);
       return this.jsonResultType;
   }

   @ResponseBody
   @RequestMapping("/upTask")
   public int upTask(Task task, HttpServletResponse response) {
       int re = taskService.upTask(task);
       return  ResponseInfo.verifyDatas(response, re);
   }


   @ResponseBody
   @RequestMapping("/updateTask")
   public int updateTask(Integer taskId,Task task,HttpServletResponse response) {
       int re = taskService.updateTaskById(taskId, task);
       return ResponseInfo.verifyDatas(response, re);
   }


   @ResponseBody
   @RequestMapping("/deleteTask")
    public int deleteTask(Integer taskId, HttpServletResponse response) {
       int re = taskService.deleteTaskById(taskId);
       return ResponseInfo.verifyDatas(response, re);
   }

}
