package com.de.service;

import com.de.entity.Task;

import java.util.List;

/**
 * @编写人:de
 * @时间:2019/10/9
 * @描述:计划任务类的业务接口
 */
public interface TaskService {

    /**
     *@描述信息：上传任务的接口
     *
     * @参数：  任务类
     * @返回值：上传的个数
     * @编写人：de
     * @时间： 2019/10/9
     */

    int upTask(Task task);


    /**
     *@描述信息：根据id删除任务
     *
     * @参数：   任务id
     * @返回值：删除任务的个数
     * @编写人：de
     * @时间： 2019/10/9
     */

    int deleteTaskById(Integer id);

    /**
     *@描述信息：批量删除
     *
     * @参数：
     * @返回值：
     * @编写人：de
     * @时间： 2019/10/30
     */
    int deleteTaskByIds(List<Integer> tIds);


    /**
     *@描述信息：查询所属员工的任务
     *
     * @参数：员工编号
     * @返回值：任务列表
     * @编写人：de
     * @时间： 2019/10/9
     */

    List<Task> selectEmpTaskByEmpId(Integer empId);

    /**
     *@描述信息：修改任务
     *
     * @参数：  修改的任务id和任务类
     * @返回值：修改成功标志
     * @编写人：de
     * @时间： 2019/10/9
     */

    int updateTaskById(Integer id, Task task);
}
