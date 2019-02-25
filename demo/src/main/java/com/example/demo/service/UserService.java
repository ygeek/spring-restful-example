package com.example.demo.service;

import com.example.demo.pojo.User;

public interface UserService {

    /**
     * Select One
     * @param id
     * @return UserDD
     */
    User getOne(int id);

    /**
     * Insert
     * @param user
     */
    boolean insert(User user);

    /**
     * Insert
     * @param user
     */
    boolean update(User user);

    /**
     * Insert
     * @param id
     */
    boolean delete(int id);


    /**
     * 用户登录
     *
     * @param username 用户名
     * @param password 密码
     * @return 操作结果
     */
    String login(String username, String password);

    /**
     * 用户注册
     *
     * @param user 用户信息
     * @return 操作结果
     */
    String register(User user);

    /**
     * 刷新密钥
     *
     * @param oldToken 原密钥
     * @return 新密钥
     */
    String refreshToken(String oldToken);

}
