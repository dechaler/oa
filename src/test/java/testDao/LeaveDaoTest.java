package testDao;

import com.de.Utils.DateUtils;
import com.de.dao.LeaveDao;
import com.de.entity.Employee;
import com.de.entity.Leave;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 * @编写人:de
 * @时间:2019/10/17
 * @描述:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class LeaveDaoTest {

    @Autowired
    private LeaveDao leaveDao;

    @Test
    public void addLeave() throws ParseException {
        Leave leave = new Leave();
        Date startTime = new Date();
        String format = "yyyy-MM-dd HH:mm:ss";
        leave.setStartTime(DateUtils.dateToStrDateTime(startTime,format));
        Date endTime = DateUtils.getFutureOrBeforeDate(startTime, 7);
        leave.setEndTime(DateUtils.dateToStrDateTime(endTime,format));
        leave.setReason("生病了");
        leave.setEmployee(new Employee(1000));
        int re = leaveDao.addLeave(leave);
        if (re > 0)
            System.out.println("添加成功");
    }

    @Test
    public void selectLeaveInfoByEmpId(){
        List<Leave> leaves = leaveDao.selectLeaveInfoByEmpId(1000);
        for (int i = 0; i < leaves.size(); i++) {
            System.out.println(leaves.get(i));
        }
    }
}
