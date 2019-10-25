package com.de.service.impl;

import com.de.Utils.DateUtils;
import com.de.Utils.KqFlag;
import com.de.Utils.KqWay;
import com.de.dao.AttendDao;
import com.de.entity.Attendance;
import com.de.service.AttendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 * @编写人:de
 * @时间:2019/10/16
 * @描述:考勤实现类
 */
@Service
public class AttendServiceImpl implements AttendService {
    @Autowired
    private AttendDao attendDao;

    //打卡，成功返回re，失败返回0
    @Override
    public int signIn(Attendance attendance) {
        String strSignTime = attendance.getSignTime();
        int kqWay = attendance.getWay();
        int kqFlag;
        try {
            Date signInDate = DateUtils.strDateTimeToDate(strSignTime, DateUtils.DATEFORMATWITHTIME);
            Date kqDate = DateUtils.getDateTime(kqWay);
//            long singTime = signInDate.getTime();
//            long kqTime = kqDate.getTime();
            if (kqWay == KqWay.SHANGBAN) {
                int result = kqDate.compareTo(signInDate);
                kqFlag = (result >= 0) ? KqFlag.ATTENDENCE : KqFlag.LATE;
                attendance.setFlag(kqFlag);
            }else if (kqWay == KqWay.XIABAN) {
                int result = kqDate.compareTo(signInDate);
                kqFlag = (result <= 0) ? KqFlag.ATTENDENCE : KqFlag.EARLY;
                attendance.setFlag(kqFlag);
            }

        } catch (ParseException e) {
        }
        int re = attendDao.signIn(attendance);

        if (re > 0){
            return re;
        }else{
            return 0;
        }
    }

//    通过员工号和考勤标识（可选）查询该员工的考勤信息
    @Override
    public List<Attendance> selectAttendInfoByEmpIdAndDateScopeAndFlag(Integer empId,String startDate,String endDate,Integer flag) {
        if ((null != flag)) {
            if ((null != startDate) && (null != endDate)) {
                List<Attendance> attendances = attendDao.selectAttendInfoByEmpIdAndDateScopeAndFlag(empId, startDate, endDate, flag);
                return attendances;
            } else{
                    List<Attendance> attendances = attendDao.selectAttendInfoByEmpIdAndDateScopeAndFlag(empId, null,null,flag);
                    return attendances;
                }
        }else if ((null != startDate) && (null != endDate)) {
            List<Attendance> attendances = attendDao.selectAttendInfoByEmpIdAndDateScopeAndFlag(empId, startDate,endDate,null);
            return attendances;
        }else{
            List<Attendance> attendances = attendDao.selectAttendInfoByEmpIdAndDateScopeAndFlag(empId, null,null,null);
            return attendances;
        }
    }
}
