import com.de.aop.Exmaop;
import com.de.service.ExmService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @编写人:de
 * @时间:2019/10/20
 * @描述:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class TestAop {

    @Autowired
    private ExmService exmService;
    @Autowired
    private Exmaop exmaop;

    private ProceedingJoinPoint joinPoint;
//    @Test
//    public void test(){
//        exmService.sayHi(1,"hahaha");
//    }


}
