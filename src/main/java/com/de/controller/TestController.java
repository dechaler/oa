package com.de.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @编写人:de
 * @日期:2019/8/19
 * @描述:
 */
@Controller
@RequestMapping("/test")
public class TestController {

    @RequestMapping("/sayHi")
    @ResponseBody
    public String sayHi(Integer name) {
        return "name=: " + name;
    }

    @RequestMapping("/jump")
    public String sayHi() {
        return "../index.jsp";  //请求转发
    }
}