package com.de.controller;

import com.de.entity.User;
import com.de.service.UserServcie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @编写人:de
 * @日期:2019/8/20
 * @描述:
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserServcie userServcie;

    @RequestMapping("/queryAll")
    public String queryAllUser(Model model){
        List<User> users = userServcie.queryAllUser();
        model.addAttribute("users",users);
        for (User user : users) {
            System.out.println(user.getId() + "," + user.getName());
        }

        return "hello.jsp";

    }

    @RequestMapping("/Hi")
    public String Hi(Model model){
        return "hi";
    }
}
