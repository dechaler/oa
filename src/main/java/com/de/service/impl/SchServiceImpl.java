package com.de.service.impl;

import com.de.dao.SchDao;
import com.de.entity.Schedule;
import com.de.service.SchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @编写人:de
 * @时间:2019/10/10
 * @描述:日程业务实现
 */
@Service
public class SchServiceImpl implements SchService {

    @Autowired
    private SchDao schDao;


    //添加成功返回个数，否则返回0,
    @Override
    public int addSch(Schedule schedule) {
        int re = schDao.addSch(schedule);
        if (re > 0){
            return re;
        }else{
            return 0;
        }
    }

    @Override
    public List<Schedule> selectSchByDepartIdAndDate(Integer departId, String date) {
        if ((null != date) && (!date.equals(""))){
            List<Schedule> schedules = schDao.selectSchByDepartIdAndDate(departId, date);
            return schedules;
        }else{
            List<Schedule> schedules = schDao.selectSchByDepartIdAndDate(departId,null);
            return schedules;
        }
    }

    @Override
    public int delSchById(Integer id) {
        int re = schDao.delSchById(id);
        if (re > 0){
            return re;
        }else{
            return 0;
        }
    }
}
