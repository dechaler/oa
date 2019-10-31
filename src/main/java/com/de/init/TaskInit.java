package com.de.init;

import com.de.Utils.DateUtils;
import com.de.dao.TaskDao;
import com.de.entity.Employee;
import com.de.entity.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * @编写人:de
 * @时间:2019/10/29
 * @描述:任务类数据初始化
 */
@Component
public class TaskInit {
    private List<Task> list = new ArrayList<>();
    @Autowired
    private TaskDao taskDao;


    public List<Task> init() throws ParseException {
        for (int i = 0; i < 100; i++) {
            Task  task = new Task();
            Date date = new Date();
            String startTime = DateUtils.dateToStrDateTime(date,DateUtils.DATEFORMATWITHTIME);
            task.setStartTime(startTime);
            int rand = 1 + (new Random().nextInt(7));
            Date date1 = DateUtils.getFutureOrBeforeDate(date,rand);
            String endTime = DateUtils.dateToStrDateTime(date1,DateUtils.DATEFORMATWITHTIME);
            task.setEndTime(endTime);
            String content = "xxx任务";
            task.setContent(content);
            Employee employee = new Employee();
//            int empId = 100000 + (new Random().nextInt(50));
            int empId = 100000;
            employee.setId(empId);
            task.setEmployee(employee);
            list.add(task);
        }
        int i = taskDao.initTaskInfo(list);
        System.out.println("成功初始化" + i + "条数据");
        return list;
    }

    public static void main(String[] args) throws ParseException {
//        int empId = 100000 + (new Random().nextInt(51));
//        System.out.println(empId);
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        TaskInit taskInit = context.getBean("taskInit", TaskInit.class);
        List<Task> init = taskInit.init();
        for (int i = 0; i < init.size(); i++) {
            System.out.println(init.get(i));
        }
        System.out.println(init.size());
    }
}
