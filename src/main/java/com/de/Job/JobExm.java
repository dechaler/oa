package com.de.Job;

import org.springframework.stereotype.Component;

/**
 * @编写人:de
 * @时间:2019/10/18
 * @描述:
 */

@Component
public class JobExm{
//    @Scheduled(cron = "5,15,25,35,45,55 * * * * *")
    public void hello(){
        System.out.println("你好我好大家好");
    }
}
