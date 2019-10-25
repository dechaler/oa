package com.de.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

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

}