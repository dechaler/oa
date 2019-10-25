package testDao;

import com.de.dao.SchDao;
import com.de.entity.Schedule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * @编写人:de
 * @时间:2019/9/9
 * @描述:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class SchDaoTest {
    @Resource
    private SchDao schDao;
    @Test
    public void testSchDao(){
//        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
//        Schedule schedule = context.getBean("Schedule", Schedule.class);
//        Employee employee = context.getBean("Employee", Employee.class);
//        schedule.setSchTask("开会");
//        schedule.setstartTime("2019-9-10 09:00:00");
//        employee.setId(1001);
//        schedule.setEmployee(employee);
//        addSch(schedule);
//        selectSchByDepartId(2);
    }

    public void addSch(Schedule schedule){
        int i = schDao.addSch(schedule);
        System.out.println("成功发布" + i + "条日程");
    }

    @Test
    public void selectSchByDepartIdAndDate(){
        List<Schedule> schedules = schDao.selectSchByDepartIdAndDate(1,"2019-10-10");
        for (Schedule schedule : schedules) {
            System.out.println(schedule);
        }


    }
    @Test
    public void delSchById(){
        int i = schDao.delSchById(3);
        if (i > 0){
            System.out.println("删除成功");
        }
    }
}
