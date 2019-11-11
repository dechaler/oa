package com.de.controller;
import com.de.Utils.JsonResultType;
import com.de.Utils.RequestParams;
import com.de.Utils.ResponseInfo;
import com.de.entity.Employee;
import com.de.entity.Task;
import com.de.service.TaskService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * @编写人:de
 * @时间:2019/10/21
 * @描述:计划任务控制器
 */
@Controller
@RequestMapping("/task")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @Autowired
    private JsonResultType<Task> jsonResultType;

    private PageInfo<Task> pageInfo;



    @ResponseBody
    @RequestMapping("/selectEmpTask")
    public JsonResultType<Task> selectEmpTask(@SessionAttribute Employee employee, RequestParams params, HttpServletResponse response) {
        int page = params.getPage();
        int limit = params.getLimit();
//        Integer empId = 100000;
       Integer empId = employee.getId();
        PageHelper.startPage(page,limit);
       List<Task> tasks = taskService.selectEmpTaskByEmpId(empId);
       pageInfo = new PageInfo<>(tasks);
       this.jsonResultType = ResponseInfo.verifyDatas(response, jsonResultType, pageInfo);
       return this.jsonResultType;
   }

   @ResponseBody
   @RequestMapping("/upTask")
   public int upTask(@SessionAttribute Employee employee, Task task, HttpServletResponse response) {
       task.setEmployee(employee);
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

    @ResponseBody
    @RequestMapping(value = "/deleteTasks",method = RequestMethod.POST)
    public int deleteTasks(String taskIds, HttpServletResponse response) {
        //取参数
//        System.out.println(taskIds);
        String substr = taskIds.substring(1, taskIds.length()- 1);
        String[] split = substr.split(",");
        List<Integer> tIds = new ArrayList<>();

        for (String s: split) {
            tIds.add(Integer.valueOf(s));
        }
        int re = taskService.deleteTaskByIds(tIds);
        re = ResponseInfo.verifyDatas(response,re);
        return re;
    }
}
