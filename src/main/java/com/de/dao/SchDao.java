package com.de.dao;

import com.de.entity.Schedule;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @编写人:de
 * @时间:2019/9/9
 * @描述:日程操作接口，只对部门经理开放
 */
public interface SchDao {

    /**
     *@描述信息：增加部门日常信息
     *
     * @参数：日常信息
     * @返回值：增加成功个数
     * @编写人：de
     * @时间： 2019/9/9
     */
    int addSch(Schedule schedule);

    /**
     *@描述信息：查看部门发布的日程
     *
     * @参数：   部门编号,和时间的日期格式（只查看当天,可选）
     * @返回值：日程集合
     * @编写人：de
     * @时间： 2019/9/9
     */

    List<Schedule> selectSchByDepartIdAndDate(@Param("id") Integer id, @Param("startTime") String date);



    /**
     *@描述信息：根据日程编号删除日程
     *
     * @参数：   日程编号
     * @返回值：影响条数
     * @编写人：de
     * @时间： 2019/9/20
     */

    int delSchById(Integer id);
}
