package com.de.dao;

import com.de.entity.Department;
import com.de.entity.Employee;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @编写人:de
 * @时间:2019/9/3
 * @描述:员工类接口
 */
@Repository
public interface EmpDao {
    /**
     *@描述信息：用于员工注册的接口
     *
     * @参数：Employee
     * @返回值：int
     * @编写人：de
     * @时间： 2019/9/3
     */
//    @Insert()
    int register(Employee employee);

    /**
     *@描述信息：登陆接口，传入员工号返回员工密码
     *
     * @参数：   员工号
     * @返回值：密码String
     * @编写人：de
     * @时间： 2019/9/3
     */
    String login(Integer id);

    /**
     *@描述信息：根据员工号更新员工信息
     *
     * @参数：  员工id，员工信息
     * @返回值：受影响个数
     * @编写人：de
     * @时间： 2019/9/3
     */
    int updateEmpById(@Param("id") Integer id,@Param("emp") Employee employee);

    /**
     *@描述信息：根据员工号更新员工密码
     *
     * @参数：   员工id，员工新密码
     * @返回值：受影响个数
     * @编写人：de
     * @时间： 2019/9/4
     */
    int updatePwdById(@Param("id") Integer id,@Param("newPwd") String newPwd);


    /**
     *@描述信息：查询全部员工信息
     *
     * @参数：
     * @返回值：List
     * @编写人：de
     * @时间： 2019/9/4
     */

    List<Employee> selectAllEmp();


    /**
     *@描述信息：根据员工id查询员工信息
     *
     * @参数：员工id
     * @返回值：员工信息
     * @编写人：de
     * @时间： 2019/9/4
     */
    Employee selectEmpById(Integer id);

    /**
     *@描述信息：根据名字查询员工信息
     *
     * @参数：   员工名
     * @返回值：员工信息
     * @编写人：de
     * @时间： 2019/10/9
     */

//    List<Employee> selectEmpByName(@Param("name") String name);

    /**
     *@描述信息：通过部门号查询部门信息，主要用来做一对多的参数
     *
     * @参数：   部门号
     * @返回值：部门信息
     * @编写人：de
     * @时间： 2019/9/8
     */

    Department selectDepartByDepartId(Integer departId);


    /**
     *@描述信息：查询所有员工号
     *
     * @参数：
     * @返回值：所有员工号集合
     * @编写人：de
     * @时间： 2019/10/17
     */

    List<Integer> selectAllEmpId();


    /**
     *@描述信息：根据员工id或者员工名查询员工信息
     *
     * @参数：员工id
     * @返回值：员工信息
     * @编写人：de
     * @时间： 2019/10/9
     */
    List<Employee> selectEmpByIdOrNameOrDepartName(@Param("id") Integer id,@Param("name") String name, @Param("departName") String departName);

    /**
     *@描述信息：初始化员工数据
     *
     * @参数：   员工列表
     * @返回值：
     * @编写人：de
     * @时间： 2019/10/28
     */

        int initEmp(List<Employee> list);
}
