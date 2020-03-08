package com.de.service.impl;

import com.de.dao.JobDao;
import com.de.entity.Job;
import com.de.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description
 * @Date 2020/3/8
 * @Author
 * @Version
 **/
@Service
public class JobServiceImpl implements JobService {

    @Autowired
    private JobDao jobDao;

    @Override
    public List<Job> selectAllJob() {
        return jobDao.selectAllJob();
    }
}
