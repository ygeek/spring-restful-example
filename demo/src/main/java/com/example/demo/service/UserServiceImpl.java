package com.example.demo.service;

import com.example.demo.dao.UserDao;
import com.example.demo.pojo.Role;
import com.example.demo.pojo.User;
import com.example.demo.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class UserServiceImpl implements  UserService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;


    @Override
    public String login(String username, String password) {
        UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authentication = authenticationManager.authenticate(upToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        return jwtTokenUtil.generateToken(userDetails);
    }

    @Override
    public String register(User user) {
        String username = user.getUserName();
        if (userDao.selectUserByUsername(username) != null) {
            return "用户已存在";
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = user.getUserPwd();
        user.setUserPwd(encoder.encode(rawPassword));
        List<Role> roles = new ArrayList<>();
        roles.add(new Role());
        user.setRoles(roles);
        userDao.insert(user);
        return "success";
    }

    @Override
    public String refreshToken(String oldToken) {
        String token = oldToken.substring("Bearer ".length());
        if (!jwtTokenUtil.isTokenExpired(token)) {
            return jwtTokenUtil.refreshToken(token);
        }
        return "error";
    }

    @Override
    public User getOne(int id) {

        return userDao.selectByPrimaryKey(id);
    }

    @Override
    public boolean insert(User user) {
        boolean flag = false;
        try {
            userDao.insert(user);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public boolean update(User user) {
        boolean flag = false;
        try {
            userDao.updateByPrimaryKey(user);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public boolean delete(int id) {
        boolean flag = false;
        try {
            userDao.deleteByPrimaryKey(id);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }


}
