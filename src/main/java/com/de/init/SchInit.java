package com.de.init;

import com.de.Utils.DateUtils;
import com.de.dao.SchDao;
import com.de.entity.Employee;
import com.de.entity.Schedule;
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
 * @时间:2019/11/1
 * @描述:
 */
@Component
public class SchInit {

    private List<Schedule> list = new ArrayList<>();
    @Autowired
    private SchDao schDao;

    public int init() throws ParseException {

        for (int i = 0; i < 500; i++) {
            Schedule schedule = new Schedule();
            schedule.setSchTask("开会");
            Date date = DateUtils.getFutureOrBeforeDate(new Date(), 1);
            schedule.setstartTime(DateUtils.dateToStrDateTime(date,DateUtils.DATEFORMATWITHTIME));
            Employee employee = new Employee();
            int empId = 100000 + (new Random().nextInt(7));
            employee.setId(empId);
            schedule.setEmployee(employee);
            list.add(schedule);
        }
        int re = schDao.initSch(list);
        return re;
    }

    public static void main(String[] args) throws ParseException {
        /*for (int i = 0; i < 100; i++) {
            int empId = 100000 + (new Random().nextInt(7));
            System.out.println(empId);
        }*/
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        SchInit schInit = context.getBean("schInit", SchInit.class);
        int re = schInit.init();

        System.out.println("初始化" + re + "日程");

    }

}
