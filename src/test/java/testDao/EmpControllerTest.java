package testDao;

import com.de.utils.JsonResultType;
import com.de.entity.Employee;
import com.de.service.EmpService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * @编写人:de
 * @时间:2019/9/5
 * @描述:测试员工controller层接口
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class EmpControllerTest {
    @Resource
    private EmpService empService;

    @Resource
    private JsonResultType<Employee> jsonResultType;

    @Test
    public void testEmpController(){
        Employee employee = new Employee();
        employee.setName("dc");
        empService.updateInfoById(100000,employee);
    }


}
