package testService;

import com.de.Utils.DateUtils;
import com.de.entity.Employee;
import com.de.entity.Task;
import com.de.service.TaskService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @编写人:de
 * @时间:2019/10/9
 * @描述:测试任务业务类
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class TaskServiceTest {

    @Autowired
    private TaskService taskService;

    @Test
    public void upTask() throws ParseException {
        Task task = new Task();
        task.setContent("今晚打老虎");
        Date currentTime = new Date();
        String startTime = DateUtils.dateToStrDateTime(currentTime, "yyyy-MM-dd HH:mm:ss");
        task.setStartTime(startTime);
        //模拟7天后的时间
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentTime);
        calendar.add(Calendar.DAY_OF_YEAR, 7);
        long fu7Time = calendar.getTime().getTime();
        Date futureDate = new Date(fu7Time);
        String endTime = DateUtils.dateToStrDateTime(futureDate, "yyyy-MM-dd HH:mm:ss");
        task.setEndTime(endTime);
        Employee employee = new Employee();
        employee.setId(1005);
        task.setEmployee(employee);

        int result = taskService.upTask(task);
        if (result > 0){
            System.out.println("成功添加" + result + "条任务");
        }
    }

    @Test
    public void deleteTaskById(){
        int re = taskService.deleteTaskById(2);
        if (re > 0) {
            System.out.println("成功删除" + re + "条任务");
        }else{
            System.out.println("删除错误");
        }

    }

    @Test
    public void selectEmpTaskByEmpId(){
        List<Task> tasks = taskService.selectEmpTaskByEmpId(1);
        if (tasks.size() > 0){
            for (Task task : tasks) {
                System.out.println(task);
            }
        }else{
            System.out.println("员工不存在");
        }

    }

    @Test
    public void updateTaskById() throws ParseException {
        Task task = new Task();
        task.setContent("今晚打老虎");
        Date currentTime = new Date();
        String startTime = DateUtils.dateToStrDateTime(currentTime, "yyyy-MM-dd HH:mm:ss");
        task.setStartTime(startTime);
        //模拟7天后的时间
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentTime);
        calendar.add(Calendar.DAY_OF_YEAR, 7);
        long fu7Time = calendar.getTime().getTime();
        Date futureDate = new Date(fu7Time);
        String endTime = DateUtils.dateToStrDateTime(futureDate, "yyyy-MM-dd HH:mm:ss");
        task.setEndTime(endTime);
        Employee employee = new Employee();
        employee.setId(1005);
        task.setEmployee(employee);

        int re = taskService.updateTaskById(3, task);
        if (re > 0)
            System.out.println("成功修改");
        else{
            System.out.println("修改失败");
        }
    }

}
