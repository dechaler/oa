package com.de.service;

import com.de.entity.Leave;

import java.util.List;

/**
 * @编写人:de
 * @时间:2019/10/23
 * @描述:请假业务接口
 */
public interface LeaveService {

    /**
     *@描述信息：添加请假信息
     *
     * @参数：请假类
     * @返回值：影响个数
     * @编写人：de
     * @时间： 2019/10/23
     */

    int addLeave(Leave leave);
    /**
     *@描述信息：查看当前员工的请假信息
     *
     * @参数：
     * @返回值：
     * @编写人：de
     * @时间： 2019/10/23
     */

    List<Leave> selectLeaveInfoByEmpId(Integer empId);

    List<Leave> selectLeaveAllInf();


    int updateStatusById(Integer status,Integer id);
}
