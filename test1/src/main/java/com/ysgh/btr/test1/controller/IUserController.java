package com.ysgh.btr.test1.controller;

import com.ysgh.btr.test1.pojo.User;
import com.ysgh.btr.test1.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;
@CrossOrigin
@RestController
@RequestMapping(value = "/user", produces = "text/html;charset=UTF-8")
public class IUserController {

    private static Integer userid = 6;
    @Autowired
    private IUserService userService;


    @PostMapping(value = "/login", params = {"username", "password"})
    public String getToken(String username, String password) throws AuthenticationException {
        return userService.login(username, password);
    }

    @PostMapping(value = "/register")
    public String register(User user) throws AuthenticationException {
        user.setUserId(userid);
        return userService.register(user);
    }

    @GetMapping(value = "/refreshToken")
    public String refreshToken(@RequestHeader String authorization) throws AuthenticationException {
        return userService.refreshToken(authorization);
    }

}