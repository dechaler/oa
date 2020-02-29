import com.de.utils.EncryptionUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @Description 测试加密类
 * @Date 2020/2/20
 * @Author
 * @Version
 **/

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class EncryptionText {

    @Test
    public void jiami(){
        String s = EncryptionUtil.md5Encryption("12345678", "100000", 1024);
        System.out.println(s);
    }
}
