package testDao;

import com.de.Utils.DateUtils;
import com.de.dao.TaskDao;
import com.de.entity.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @编写人:de
 * @时间:2019/9/9
 * @描述:测试任务接口
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class TaskDaoTest {

    @Resource
    private TaskDao taskDao;

    @Test
    public void TestTaskDao(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        Task task = context.getBean("Task", Task.class);
//     for (int i = 0; i < ; i++) {
//            upTask();
//        }
//       deleteTaskById(1);
//       selectEmpTaskByEmpId(1000);

    }



    @Test
    public void upTask() throws ParseException {
        Task task = new Task();
//        task.setContent("作报告");
//        task.setStartTime(DateUtils.dateToStrDateTime(new Date(),"yyyy-MM-dd"));
//        task.setEndTime(DateUtils.dateToStrDateTime(DateUtils.getFutureOrBeforeDate(new Date(),7),"yyyy-MM-dd"));
//        Employee emp = new Employee();
//        emp.setId(1000);
//        task.setEmployee(emp);
        int i = taskDao.upTask(task);
        System.out.println("发布成功，发布" + i + "条任务!!");
    }

    public void deleteTaskById(Integer id){
        int i = taskDao.deleteTaskById(id);
        if (i > 0){
            System.out.println("成功删除" + 1 + "条任务");
        }
    }

    @Test
    public void selectEmpTaskByEmpId(){
        List<Task> tasks = taskDao.selectEmpTaskByEmpId(100000);
        for (Task task : tasks) {
            System.out.println(task);
        }

    }
    @Test
    public void updateTaskById() throws ParseException {
        Task task = new Task();
        task.setContent("吃饭");
        task.setStartTime(DateUtils.dateToStrDateTime(new Date(),DateUtils.DATEFORMATWITHTIME));
        task.setEndTime(DateUtils.dateToStrDateTime(new Date(),DateUtils.DATEFORMATWITHTIME));
        int i = taskDao.updateTaskById(728, task);
            System.out.println("成功修改" + i + "条任务");
    }

    @Test
    public void deleteTaskByIds() {
        List<Integer> list = new ArrayList<>();
        list.add(522);
        list.add(521);
        list.add(524);
        int i = taskDao.deleteTaskByIds(list);
        System.out.println("成功删除 " + i + "条数据");
    }
}
