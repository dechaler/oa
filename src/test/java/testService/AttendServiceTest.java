package testService;

import com.de.Utils.DateUtils;
import com.de.Utils.KqFlag;
import com.de.entity.Attendance;
import com.de.entity.Employee;
import com.de.service.AttendService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 * @编写人:de
 * @时间:2019/10/16
 * @描述:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class AttendServiceTest {
    @Autowired
    private AttendService attendService;
    private static ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

    @Test
    public void signIn() throws ParseException {
        Attendance attendance = context.getBean("attendance", Attendance.class);
        Employee employee = context.getBean("employee", Employee.class);
        Date date = new Date();
        String dateTime = DateUtils.dateToStrDateTime(date, "yyyy-MM-dd HH:mm:ss");
        attendance.setSignTime(dateTime);
        String AttDate = DateUtils.dateToStrDateTime(date, "yyyy-MM-dd");
        attendance.setAttendDate(AttDate);
        attendance.setWay(0);
        attendance.setFlag(KqFlag.ATTENDENCE);
        employee.setId(1002);
        attendance.setEmployee(employee);
        attendService.signIn(attendance);
    }

    @Test
    public void selectAttendInfoByEmpIdAndFlag() throws ParseException {
        String format = "yyyy-MM-dd";
        Date date = DateUtils.getFutureOrBeforeDate(new Date(), -1);
        String endDate = DateUtils.dateToStrDateTime(new Date(),format);
        String startDate = DateUtils.dateToStrDateTime(date,format);
        List<Attendance> attendances = attendService.selectAttendInfoByEmpIdAndDateScopeAndWay(1000, null,null,null);
        for (Attendance attendance : attendances) {
            System.out.println(attendance);
        }
    }
}
