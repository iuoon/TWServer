package com.iuoon.tw.controller;

import com.iuoon.tw.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户操作controller
 * Created by mwuyz on 2016/8/30.
 */
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login",method = {RequestMethod.GET,RequestMethod.POST})
    public String login(@RequestParam String userName,@RequestParam String password){
        return userService.login(userName,password);
    }

}
