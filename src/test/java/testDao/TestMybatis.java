package testDao;

import com.de.dao.DepartDao;
import com.de.dao.UserDao;
import com.de.entity.Department;
import com.de.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @编写人:de
 * @日期:2019/8/20
 * @描述:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class TestMybatis {
    @Autowired
    private UserDao userDao;
    @Test
    public void testUserDao(){
        List<User> users = userDao.queryAllUser();
        for(User user:users) {
            System.out.println(user.getId() + "," + user.getName());
        }
    }

    @Autowired
    private DepartDao departDao;
    @Test
    public void testDepartDao(){
        Department department = departDao.selectDepartById(1);
        System.out.println(department.getId() + "," +department.getName());
    }


}
