package com.example.demo.service;

import com.example.demo.dao.UserDao;
import com.example.demo.pojo.JwtUser;
import com.example.demo.pojo.Role;
import com.example.demo.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Primary
public class JwtUserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userDao.selectUserByUsername(s);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", s));
        } else {
            List<GrantedAuthority> authorities = new ArrayList<>();
            for (Role role : user.getRoles()) {
                authorities.add(new SimpleGrantedAuthority(role.getRolename()));
            }
            return new JwtUser(user.getUserName(), user.getUserPwd(), authorities);
        }
    }
}
