package za;

import com.de.za.interf;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @编写人:de
 * @时间:2019/10/21
 * @描述:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class interfTest {

    @Autowired
    private interf interf;

    @Test
    public void interfTest(){
        interf.xixi();
    }
}
