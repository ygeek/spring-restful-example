package com.ysgh.btr.test1.service;

import com.ysgh.btr.test1.pojo.User;

public interface IUserService {

    String login(String username, String password);

    String register(User user);

    String refreshToken(String oldToken);
}
