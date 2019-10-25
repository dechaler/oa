package com.de.dao;

import com.de.entity.Leave;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @编写人:de
 * @时间:2019/9/20
 * @描述:请假接口
 */
public interface LeaveDao {


    /**
     *@描述信息：添加请假信息
     *
     * @参数：请假类
     * @返回值：影响个数
     * @编写人：de
     * @时间： 2019/9/20
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

    List<Leave> selectLeaveInfoByEmpId(@Param("empId") Integer empId);
}
