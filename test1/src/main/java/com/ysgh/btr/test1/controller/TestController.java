package com.ysgh.btr.test1.controller;

import com.ysgh.btr.test1.dao.UserDao;
import com.ysgh.btr.test1.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController

@RequestMapping("/user")
public class TestController {
    @Autowired
    private UserDao userDao;
    @RequestMapping(method = RequestMethod.POST)
    public Integer addUser(User user) {
        return userDao.insert(user);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public Integer deleteUser(@RequestParam(value = "userId", required = true) int userId) {
        return userDao.deleteByPrimaryKey(userId);
    }

    @RequestMapping( method = RequestMethod.PUT)
    public Integer updateUser(User user) {
        return userDao.updateByPrimaryKey(user);
    }

    @RequestMapping( method = RequestMethod.GET)
    public User findOneUser(@RequestParam(value = "userId", required = true) int userId) {
        return userDao.selectByPrimaryKey(userId);
    }

    @RequestMapping(value = "/name" , method = RequestMethod.GET)
    public User findUserByName(@RequestParam(value="userName",required = true) String userName){
        return userDao.selectUserByUsername(userName);
    }
}
