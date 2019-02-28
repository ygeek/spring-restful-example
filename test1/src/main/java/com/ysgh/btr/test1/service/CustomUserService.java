package com.ysgh.btr.test1.service;

import com.ysgh.btr.test1.dao.UserDao;
import com.ysgh.btr.test1.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 用户验证方法
 */
@Service
@Primary
public class CustomUserService implements UserDetailsService {

    @Autowired
    UserDao userDao;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.selectUserByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException("UserName Not Exist");
        }
        return user;
    }
}
