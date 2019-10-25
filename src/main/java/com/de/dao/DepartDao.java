package com.de.dao;

import com.de.entity.Department;

import java.util.List;

/**
 * @编写人:de
 * @时间:2019/9/8
 * @描述:部门接口
 */
public interface DepartDao {

    /**
     *@描述信息：
     *
     * @参数：
     * @返回值：
     * @编写人：de
     * @时间： 2019/9/8
     */

    Department selectDepartById(Integer id);


    /**
     *@描述信息：查询全部部门的信息
     *
     * @参数：
     * @返回值：部门信息列表
     * @编写人：de
     * @时间： 2019/9/8
     */

    List<Department> selectAllDepart();
}
