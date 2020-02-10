package com.de.service;

import com.de.entity.Attendance;

import java.util.List;

/**
 * @编写人:de
 * @时间:2019/10/16
 * @描述:考勤的业务接口
 */
public interface AttendService {

    /**
     *@描述信息：打卡
     *
     * @参数：考勤信息
     * @返回值：考勤成功返回标志
     * @编写人：de
     * @时间： 2019/10/12
     */

    int signIn(Attendance attendance);

    /**
     *@描述信息：初始化每天的打卡信息
     * @参数：
     * @返回值：
     * @编写人：de
     * @时间： 2019/12/21
     */

    List<Attendance> selectClockInInfo(Integer empId);


/**
 *@描述信息：通过员工号,查看考勤信息
 *
 * @参数：
 * @返回值：
 * @编写人：de
 * @时间： 2020/2/3
 */

    List<Attendance>  selectAttendInfoByEmpId(Integer empId);

    /**
     *@描述信息：通过员工号,考勤方式（可选）,考勤日期范围(可选)查看考勤信息
     *
     * @参数：员工号
     * @返回值：返回该员工的考情信息
     * @编写人：de
     * @时间： 2019/10/16
     */

    List<Attendance> selectAttendInfoByEmpIdAndDateScopeAndWay(Integer empId,String startDate,String endDate,Integer way);
}
