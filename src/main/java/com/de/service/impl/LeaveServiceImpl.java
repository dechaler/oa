package com.de.service.impl;

import com.de.dao.LeaveDao;
import com.de.entity.Leave;
import com.de.service.LeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @编写人:de
 * @时间:2019/10/23
 * @描述:
 */
@Service
public class LeaveServiceImpl implements LeaveService {

    @Autowired
    private LeaveDao leaveDao;


    @Override
    public int addLeave(Leave leave) {
        int re = leaveDao.addLeave(leave);
        return re;
    }

    @Override
    public List<Leave> selectLeaveInfoByEmpId(Integer empId) {
        List<Leave> leaves = leaveDao.selectLeaveInfoByEmpId(empId);
        return leaves;
    }
}
