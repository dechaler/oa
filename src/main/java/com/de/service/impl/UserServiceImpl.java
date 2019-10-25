package com.de.service.impl;

import com.de.dao.UserDao;
import com.de.entity.User;
import com.de.service.UserServcie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @编写人:de
 * @日期:2019/8/20
 * @描述:
 */
@Service
public class UserServiceImpl implements UserServcie {
    @Autowired
    private UserDao userDao;

    public List<User> queryAllUser() {
        return userDao.queryAllUser();
    }
}
