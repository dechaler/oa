package com.de.service.impl;

import com.de.dao.EmpDao;
import com.de.entity.Employee;
import com.de.service.EmpService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @编写人:de
 * @时间:2019/9/4
 * @描述:
 */
@Service
public class EmpServiceImpl implements EmpService {
    @Resource
    private EmpDao empDao;


    @Override
    public List<Employee> selectAllEmp() {
        List<Employee> employees = empDao.selectAllEmp();
        return employees;
    }


    //登陆业务，成功返回1，失败返回0
    @Override
    public int login(Integer id, String pwd) {
        String result = empDao.login(id);
        if (null != result && result.equals(pwd)) {
            return 1;
        }
        return 0;
    }

    @Override
    public int updateEmpById(Integer id, Employee employee) {
        int result = empDao.updateEmpById(id, employee);
        if (result > 0)
            return result;
        else
            return 0;
    }

    @Override
    public int updatePwdById(Integer id, String newPwd) {
        int result = empDao.updatePwdById(id, newPwd);
        if (result > 0)
            return result;
        else
            return 0;

    }

    @Override
    public List<Employee> selectEmpByIdOrNameOrDepartName(Integer id, String name, String departName) {
        List<Employee> employees = empDao.selectEmpByIdOrNameOrDepartName(id, name, departName);
        return employees;
    }

    @Override
    public Employee selectEmpById(Integer id) {
        return empDao.selectEmpById(id);
    }


//    @Override
//    public Employee selectEmpById(Integer id) {
//        return empDao.selectEmpById(id);
//    }

}
