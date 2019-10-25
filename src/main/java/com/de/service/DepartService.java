package com.de.service;

import com.de.entity.Department;

import java.util.List;

/**
 * @编写人:de
 * @时间:2019/10/9
 * @描述:部门业务接口
 */
public interface DepartService {

    Department selectDepartById(Integer id);

    List<Department> selectAllDepart();


}
