package com.de.service;

import com.de.entity.Employee;

import java.util.List;

/**
 * @编写人:de
 * @时间:2019/9/4
 * @描述:员工操作接口
 */
public interface EmpService {

    List<Employee> selectAllEmp();

    /**
     *@描述信息：根据页码查询员工信息
     *
     * @参数：  页码，显示数据条数
     * @返回值：分页后的员工数据
     * @编写人：de
     * @时间： 2019/9/5
     */

//    PageInfo<Employee> selectAllEmpByPage(int page, int limit);



    /**
     *@描述信息：登陆
     *
     * @参数：   登陆的员工号，密码
     * @返回值：成功（1）失败（0）
     * @编写人：de
     * @时间： 2019/10/9
     */

     int login(Integer id,String pwd);


    /**
     *@描述信息：根据员工号更新员工信息
     *
     * @参数：  员工id，员工信息
     * @返回值：受影响个数
     * @编写人：de
     * @时间： 2019/10/9
     */
    int updateEmpById(Integer id,Employee employee);

    /**
     *@描述信息：根据员工号更新员工密码
     *
     * @参数：   员工id，员工新密码
     * @返回值：受影响个数
     * @编写人：de
     * @时间： 2019/10/9
     */
    int updatePwdById(Integer id, String newPwd);

    /**
     *@描述信息：根据员工id或者员工名查询员工信息
     *
     * @参数：员工id
     * @返回值：员工信息
     * @编写人：de
     * @时间： 2019/10/9
     */
    List<Employee> selectEmpByIdOrNameOrDepartName(Integer id, String name, String departName);

    /**
     *@描述信息：根据员工名查询员工信息
     *
     * @参数：员工名
     * @返回值：员工信息
     * @编写人：de
     * @时间： 2019/10/9
     */
//    List<Employee> selectEmpByName(String name);



    /**
     *@描述信息：根据员工id查询员工信息
     *
     * @参数：员工id
     * @返回值：员工信息
     * @编写人：de
     * @时间： 2019/9/4
     */
    Employee selectEmpById(Integer id);

}
