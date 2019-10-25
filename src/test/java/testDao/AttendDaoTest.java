package testDao;

import com.de.Utils.DateUtils;
import com.de.Utils.KqFlag;
import com.de.dao.AttendDao;
import com.de.entity.Attendance;
import com.de.entity.Employee;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 * @编写人:de
 * @时间:2019/9/12
 * @描述:考勤类的测试
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class AttendDaoTest {

    private static ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
    @Resource
    private AttendDao attendDao;
    @Test
    public void testAttendDao() throws ParseException {
        Attendance attendance = context.getBean("Attendance", Attendance.class);
        Employee employee = context.getBean("employee", Employee.class);
        Date date = new Date();
        String dateTime = DateUtils.dateToStrDateTime(date, "yyyy-MM-dd HH:mm:ss");
        attendance.setSignTime(dateTime);
        String AttDate = DateUtils.dateToStrDateTime(date, "yyyy-MM-dd");
        attendance.setAttendDate(AttDate);
        attendance.setWay(0);
        attendance.setFlag(KqFlag.ATTENDENCE);
        employee.setId(1001);
        attendance.setEmployee(employee);
//        signIn(attendance);

//        selectAllAttendInfo();

    }


    @Test
    public void signIn() throws ParseException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        Attendance attendance = context.getBean("Attendance", Attendance.class);
        Employee employee = context.getBean("employee", Employee.class);
        Date date = new Date();
        String dateTime = DateUtils.dateToStrDateTime(date, "yyyy-MM-dd HH:mm:ss");
        attendance.setSignTime(dateTime);
        String AttDate = DateUtils.dateToStrDateTime(date, "yyyy-MM-dd");
        attendance.setAttendDate(AttDate);
        attendance.setWay(0);
        attendance.setFlag(KqFlag.ATTENDENCE);
        employee.setId(1001);
        attendance.setEmployee(employee);
        int i = attendDao.signIn(attendance);
        if(i > 0) {
            System.out.println("成功考勤");
        }

    }

    @Test
    public void selectAllAttendInfo(){
        List<Attendance> attendances = attendDao.selectAllAttendInfo();
        for (Attendance a : attendances) {
            System.out.println(a);
        }

    }

    @Test
    public void selectAttendInfoByEmpIdAndDateScopeAndFlag() throws ParseException {
        String format = "yyyy-MM-dd";
        String endDate = DateUtils.dateToStrDateTime(new Date(),format);
        Date date = DateUtils.getFutureOrBeforeDate(new Date(), -1);
        String startDate = DateUtils.dateToStrDateTime(date,format);
        List<Attendance> attendances = attendDao.selectAttendInfoByEmpIdAndDateScopeAndFlag(1000,startDate,endDate,null);
        for (Attendance attendance : attendances){
            System.out.println(attendance);
        }
    }
}
