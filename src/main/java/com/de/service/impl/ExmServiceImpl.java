package com.de.service.impl;

import com.de.service.ExmService;
import org.springframework.stereotype.Service;

/**
 * @编写人:de
 * @时间:2019/10/20
 * @描述:
 */
@Service("exmService")
public class ExmServiceImpl implements ExmService {

    @Override
    public String sayHi(int count,String msg) {
        System.out.println("实现类=========" + count + "========" + msg);
        return msg;
    }

    @Override
    public void haha() {
        System.out.println("haha");
    }
}
