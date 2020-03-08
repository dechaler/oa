package com.de.service.impl;

import com.de.dao.DepartDao;
import com.de.entity.Department;
import com.de.service.DepartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @编写人:de
 * @时间:2019/10/9
 * @描述:处理部门的业务逻辑
 */
@Service
public class DepartServiceImpl implements DepartService {

    @Autowired
    private DepartDao departDao;


    @Override
    public Department selectDepartById(Integer id) {
        //六大部门编号
        if(id > 0 && id <= 6)
            return departDao.selectDepartById(id);
        return null;
    }

    @Override
    public List<Department> selectAllDepart() {
        return departDao.selectAllDepart();
    }

    @Override
    public List<Department> selectAllDepartIdAndName() {
        return departDao.selectAllDepartIdAndName();
    }
}
