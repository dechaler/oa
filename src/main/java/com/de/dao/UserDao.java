package com.de.dao;

import com.de.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @编写人:de
 * @日期:2019/8/20
 * @描述:用户第一个接口 用来测试搭建项目是否成功
 */
@Repository
public interface UserDao {
     List<User> queryAllUser();
}
