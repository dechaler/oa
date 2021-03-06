package testService;

import com.de.entity.Department;
import com.de.service.DepartService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @编写人:de
 * @时间:2019/10/9
 * @描述:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class DepartServiceTest {

    @Autowired
    private DepartService departService;

    @Test
    public void queryDepartById(){
        Department department = departService.selectDepartById(2);
        System.out.println(department);
    }
}
