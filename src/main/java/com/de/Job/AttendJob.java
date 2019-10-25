package com.de.Job;

import com.de.Utils.DateUtils;
import com.de.Utils.KqWay;
import com.de.dao.AttendDao;
import com.de.dao.EmpDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 * @编写人:de
 * @时间:2019/10/17
 * @描述:
 */
@Component
public class AttendJob {
    @Autowired
    private  EmpDao empDao;
    @Autowired
    private AttendDao attendDao;

//    public void selectEmpById(){
//////        Employee employee = empDao.selectEmpById(1000);
////////        System.out.println(employee);
//////    }

    public void InitAttendInfoWithShangBan() throws ParseException {
        List<Integer> ids = empDao.selectAllEmpId();
        String dateTime = DateUtils.dateToStrDateTime(new Date(), "yyyy-MM-dd");
        int re = attendDao.InitAttendInfo(dateTime, KqWay.SHANGBAN, ids);
        System.out.println("日期：" + dateTime + " 初始化" + re  + "条上班考勤信息");
    }

    public void InitAttendInfoWithXiaBan() throws ParseException {
        List<Integer> ids = empDao.selectAllEmpId();
        String dateTime = DateUtils.dateToStrDateTime(new Date(), "yyyy-MM-dd");
        int re = attendDao.InitAttendInfo(dateTime, KqWay.XIABAN, ids);
        System.out.println("日期：" + dateTime + " 初始化" + re  + "条下班考勤信息");
    }





    public static void main(String[] args) throws ParseException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
//        attendJob.empDao = context.getBean("empDao", EmpDao.class);
//        List<Integer> ids = attendJob.empDao.selectAllEmpId();
//        attendJob.attendDao = context.getBean("attendDao", AttendDao.class);
//        String dateTime = DateUtils.dateToStrDateTime(new Date(), "yyyy-MM-dd");
//        int re = attendJob.attendDao.InitAttendInfo(dateTime, KqWay.SHANGBAN, ids);
//        if (re > 0){
//            System.out.println("初始化" + re + "条考勤信息");
//        }else{
//            System.out.println("初始化失败");
//
//        }
        AttendJob attendJob = context.getBean("attendJob", AttendJob.class);
        attendJob.InitAttendInfoWithShangBan();
//        attendJob.InitAttendInfoWithXiaBan();
    }

}
