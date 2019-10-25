package com.de.dao;

import com.de.entity.Attendance;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @编写人:de
 * @时间:2019/9/12
 * @描述:
 */

@Repository
public interface AttendDao {
    /**
     *@描述信息：打卡
     *
     * @参数：考勤信息
     * @返回值：考勤成功返回标志
     * @编写人：de
     * @时间： 2019/9/12
     */
    
    int signIn(Attendance attendance);

//    int signInWithNoFlag(Attendance attendance);


    /**
     *@描述信息：查询所有考勤信息
     *
     * @参数：
     * @返回值：考勤信息的集合
     * @编写人：de
     * @时间： 2019/9/12
     */

    List<Attendance> selectAllAttendInfo();

    /**
     *@描述信息：通过员工号,出勤标识（可选）,考勤日期范围(可选)查看考勤信息
     *
     * @参数：员工号
     * @返回值：返回该员工的考情信息
     * @编写人：de
     * @时间： 2019/10/16
     */

    List<Attendance> selectAttendInfoByEmpIdAndDateScopeAndFlag(@Param("empId") Integer empId,@Param("startDate") String startDate,@Param("endDate") String endDate,@Param("flag") Integer flag);


//    List<Attendance> selectAttendInfoByEmpIdAndDateScopeAndFlag

/**
 *@描述信息：初始化所有员工每天的考勤信息
 *
 * @参数：   考勤日期，考勤方式，全体员工号
 * @返回值：影响个数
 * @编写人：de
 * @时间： 2019/10/17
 */

    int InitAttendInfo(@Param("kqDate") String kqDate,@Param("kqWay")
            int kqWay, @Param("empIds") List<Integer> empId);
}
