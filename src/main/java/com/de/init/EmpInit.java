package com.de.init;

import com.de.Utils.DateUtils;
import com.de.dao.EmpDao;
import com.de.entity.Employee;
import com.de.entity.Job;
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
 * @时间:2019/10/27
 * @描述:初始化员工信息
 */
@Component
public class EmpInit {
    private List<Employee> list = new ArrayList<>();
    @Autowired
    private EmpDao empDao;
    private static String[] telFirst="134,135,136,137,138,139,150,151,152,157,158,159,130,131,132,155,156,133,153".split(",");


    public  List<Employee> init() throws ParseException {
        for (int i = 1; i < 50; i++) {
            Employee employee = new Employee();
            Job job = new Job();
            //随机数
            int rand = new Random().nextInt(33);
            int age = 18 + rand;
            employee.setAge(age);
            String name = "emp" + i;
            employee.setName(name);
            String pwd = "12345678";
            employee.setPassword(pwd);
            String sex;
            if (rand % 2 == 0) {
                    sex = "男";
                }else {
                    sex = "女";
                }
            employee.setSex(sex);
            employee.setPhone(Long.valueOf(getTel()));
            int job_id = 1 + new Random().nextInt(10);
            job.setId(job_id);
            employee.setJob(job);
            String dateTime = DateUtils.dateToStrDateTime(new Date(), DateUtils.DATEFORMATWITHDATE);
            employee.setInerDate(dateTime);
            list.add(employee);
        }
        int i = empDao.initEmp(list);
        System.out.println("成功初始化" + i + "条数据");
        return list;

    }

    //生成随机手机号
    private static String getTel() {
        int index=getNum(0,telFirst.length-1);
        String first=telFirst[index];
        String second=String.valueOf(getNum(1,888)+10000).substring(1);
        String third=String.valueOf(getNum(1,9100)+10000).substring(1);
        return first+second+third;
    }
    public static int getNum(int start,int end) {
        return (int)(Math.random()*(end-start+1)+start);
    }

    public static void main(String[] args) throws ParseException {

//        int i = new Random().nextInt(32);
//        int age = 18 + i;
//        System.out.println(age);
//       String tel = getTel();
//        System.out.println(tel);
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        EmpInit empInit = context.getBean("empInit", EmpInit.class);
        List<Employee> init = empInit.init();
        for (Employee employee : init){
            System.out.println(employee);
        }
    }
}
