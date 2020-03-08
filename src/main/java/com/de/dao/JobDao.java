package com.de.dao;

import com.de.entity.Job;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description
 * @Date 2020/3/8
 * @Author
 * @Version
 **/
@Repository
public interface JobDao {

    List<Job> selectAllJob();
}
