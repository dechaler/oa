package testDao;

import com.de.dao.EmpDao;
import com.de.entity.Employee;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * @编写人:de
 * @时间:2019/9/3
 * @描述:测试EmpDao接口
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class EmpDaoTest {
    @Resource
    private EmpDao empDao;
    @Test
    public void testEmpDao(){
//        testRegister();
//        testLogin();
//        testUpdateEmpById();
//        testUdatePwdById();
        testSelectAllEmp();
//        testselectEmpById();


    }




    @Test
    public void testRegister(){
        Employee emp = new Employee();
        emp.setName("小明");
        emp.setPassword("123");
        int count = empDao.register(emp);
        System.out.println("插入数据" + count +"条");
        System.out.println(emp);
    }
    public void testLogin(){
        String pwd = empDao.login(1000);
        System.out.println("密码 " + pwd);
    }

    public void testUpdateEmpById(){
        Employee emp = new Employee();
        emp.setName("dd1");
        emp.setPassword("12345");
        emp.setAge(88);
        empDao.register(emp);
        System.out.println(emp);
        emp.setAge(18);
        int i = empDao.updateEmpById(emp.getId(), emp);
        System.out.println(emp);
    }


    public void testUdatePwdById() {
        int i = empDao.updatePwdById(1014, "111");
        if (i > 0){
            System.out.println("更改密码成功");
        }
    }

    @Test
    public void testSelectAllEmp(){
        List<Employee> employees = empDao.selectAllEmp();
        for (Employee employee : employees) {
            System.out.println(employee);
        }
    }

    @Test
    public void selectAllEmpId(){
        List<Integer> integers = empDao.selectAllEmpId();
        System.out.println(integers);
    }

    @Test
    public void selectEmpByIdOrName(){
        List<Employee> employees = empDao.selectEmpByIdOrNameOrDepartName(null, null,null);
        for (Employee employee:employees){
            System.out.println(employee);
        }
    }
    @Test
    public void selectEmpById(){
        Employee employee = empDao.selectEmpById(1001);
        System.out.println(employee);
    }
}


