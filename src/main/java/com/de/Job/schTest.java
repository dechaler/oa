package com.de.Job;

import org.quartz.SchedulerException;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @编写人:de
 * @时间:2019/10/19
 * @描述:
 */
//@Component
public class schTest {
//    @Autowired
//    private ClassPathXmlApplicationContext context;
    public static void main(String[] args) throws SchedulerException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
//        Scheduler scheduler = (Scheduler)context.getBean("schedulerFactoryBean");
//        scheduler.start();





    }
}
