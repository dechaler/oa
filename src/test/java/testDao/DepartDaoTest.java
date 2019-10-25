package testDao;

import com.de.dao.DepartDao;
import com.de.entity.Department;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * @编写人:de
 * @时间:2019/9/8
 * @描述:测试DeparDao接口
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class DepartDaoTest {

    @Resource
    private DepartDao departDao;
    @Test
    public void testDepartDao() {
        queryAllDepart();
    }

    @Test
    public void queryAllDepart(){
        List<Department> departments = departDao.selectAllDepart();
        for (Department department : departments) {
            System.out.println(department);
        }
    }
    @Test
    public void queryDepartById(){
        Department department = departDao.selectDepartById(1);
        System.out.println(department);
    }
}
