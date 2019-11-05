package com.intecsec.springboot.controller;

import com.intecsec.springboot.entity.User;
import com.intecsec.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @description:
 * @author: peter.peng
 * @create: 2019-10-27 10:22
 **/
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "get")
    public User getList() {
       return userService.getUser(1);
    }

    @GetMapping(value = "update")
    public User update(@RequestParam Map<String, String> param) {

        User user = new User();
        user.setAge(Integer.parseInt(param.get("age")));
        user.setId(1);
        userService.updateUser(user);

        return user;
    }

    @GetMapping(value = "batch")
    public String batch(@RequestParam Map<String, String> param) {
        userService.batchInsertLog();
        return "OK";
    }

}
