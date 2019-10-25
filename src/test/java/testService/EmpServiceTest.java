package testService;

import com.de.entity.Employee;
import com.de.service.EmpService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @编写人:de
 * @时间:2019/10/9
 * @描述:测试员工service接口
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class EmpServiceTest {

    @Autowired
    private EmpService empService;

    @Test
    public void login(){
        int flag = empService.login(1000, "333");
        if(flag > 0){
            System.out.println("登陆成功");
        }else{
            System.out.println("登陆失败");
        }

    }

    @Test

    public void updateEmpById(){

        Employee employee = new Employee();
        employee.setId(1000);
        employee.setAge(18);
        employee.setName("邓超");
        employee.setPhone(11111111111L);
        int i = empService.updateEmpById(1005, employee);
        if (i > 0){
            System.out.println("更新成功");
        }else{
            System.out.println("失败");
        }
    }

    @Test
    public void updatePwdById(){
        int result = empService.updatePwdById(1005, "ooo");
        if (result > 0) {
            System.out.println("更新密码成功");
        }else{
            System.out.println("更新密码失败");
        }


    }

}
