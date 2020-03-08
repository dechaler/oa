package com.de.service.impl;

import com.de.dao.EmpDao;
import com.de.dao.FileDao;
import com.de.dao.LeaveDao;
import com.de.dao.TaskDao;
import com.de.entity.Employee;
import com.de.service.EmpService;
import com.de.utils.EncryptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Autowired
    private FileDao fileDao;

    @Autowired
    private TaskDao taskDao;

    @Autowired
    private LeaveDao leaveDao;

    @Override
    public List<Employee> selectAllEmp() {
        List<Employee> employees = empDao.selectAllEmp();
        return employees;
    }


    //登陆业务，成功返回1，失败返回0
    @Override
    public int login(Integer id, String pwd) {
        String result = empDao.login(id);
        String temp = EncryptionUtil.md5Encryption(pwd, id + "", 1024);
        if (null != result && result.equals(temp)) {
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
    public int updateInfoById(Integer id, Employee employee) {
        int re = empDao.updateInfoById(id, employee);
        return re;
    }

    @Override
    public int updatePwdById(Integer id, String newPwd) {
        newPwd = EncryptionUtil.md5Encryption(newPwd,id + "",1024);
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
    public int addEmp(Employee employee) {
        Integer id = employee.getId();
        Employee temp = empDao.selectEmpById(id);
        if (temp != null) {
            return -1;
        }
        return empDao.addEmp(employee);
    }

    @Override
    public Employee selectEmpById(Integer id) {
        return empDao.selectEmpById(id);
    }

    @Override
    @Transactional
    public int delEmpById(Integer id) {
        fileDao.delFileByEmpId(id);
        taskDao.delTaskByEmpId(id);
        leaveDao.delLeaveByEmpId(id);
        return empDao.delEmpById(id);
    }



//    @Override
//    public Employee selectEmpById(Integer id) {
//        return empDao.selectEmpById(id);
//    }

}
