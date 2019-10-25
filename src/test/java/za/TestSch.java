package za;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @编写人:de
 * @时间:2019/10/18
 * @描述:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class TestSch {

//    private SchedulerFactoryBean schedulerFactoryBean;


    @Test
    public void test() throws SchedulerException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        Scheduler scheduler = (Scheduler)context.getBean("schedulerFactoryBean");
        scheduler.start();
//        Object object = context.getBean("schedulerFactoryBean");
//        System.out.println(object.getClass().getName() + "==================");

    }


    public static void main(String[] args) throws SchedulerException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        Scheduler scheduler = (Scheduler)context.getBean("schedulerFactoryBean");
        scheduler.start();
    }

}
