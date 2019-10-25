import com.de.entity.User;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

/**
 * @编写人:de
 * @日期:2019/8/19
 * @描述:
 *
 */
@Service
public class SpringTest {
  @Test
    public void testSpring(){
      ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
      User user = (User) context.getBean("User");
  }

  public static void main(String[] args) {
    ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
    User user = (User) context.getBean("User");
  }
}
