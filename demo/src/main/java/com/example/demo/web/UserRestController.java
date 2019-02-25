package com.example.demo.web;

import com.example.demo.pojo.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.AuthenticationException;
@CrossOrigin
@RestController
@RequestMapping(value = "/api", produces = "text/html;charset=UTF-8")
public class UserRestController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public boolean addUser(User user) {
        return userService.insert(user);
    }

    @RequestMapping(value = "/user", method = RequestMethod.DELETE)
    public boolean deleteUser(@RequestParam(value = "userId", required = true) int userId) {
        return userService.delete(userId);
    }

    @RequestMapping(value = "/user", method = RequestMethod.PUT)
    public boolean updateUser(User user) {
        return userService.update(user);
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public User findOneUser(@RequestParam(value = "userId", required = true) int userId) {
        System.out.println(userService.getOne(userId));
        return userService.getOne(userId);
    }
    /**
     * 用户登录
     *
     * @param username 用户名
     * @param password 密码
     * @return 操作结果
     * @throws AuthenticationException 错误信息
     */
    @PostMapping(value = "/login", params = {"username", "password"})
    public String getToken(String username, String password) throws AuthenticationException {
        return userService.login(username, password);
    }

    /**
     * 用户注册
     *
     * @param user          用户信息
     * @return 操作结果
     * @throws AuthenticationException 错误信息
     */
    @PostMapping(value = "/register")
    public String register(User user) throws AuthenticationException {
        return userService.register(user);
    }

    /**
     * 刷新密钥
     *
     * @param authorization 原密钥
     * @return 新密钥
     * @throws AuthenticationException 错误信息
     */
    @GetMapping(value = "/refreshToken")
    public String refreshToken(@RequestHeader String authorization) throws AuthenticationException {
        return userService.refreshToken(authorization);
    }


}