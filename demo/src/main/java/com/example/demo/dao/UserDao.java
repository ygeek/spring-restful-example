package com.example.demo.dao;

import com.example.demo.pojo.Role;
import com.example.demo.pojo.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface UserDao {

    @Delete("delete from public.user where id=#{userId}")
    int deleteByPrimaryKey(Integer userId);

    @Insert("insert into public.user values(#{userId},#{userName},#{userPwd},#{version})")
    int insert(User record);

    @Select("select * from public.user where id = #{userId}")
    User selectByPrimaryKey(Integer userId);

    @Update("update public.user set user_name=#{userName},user_pwd=#{userPwd},version=#{version} where id =#{userId}")
    int updateByPrimaryKey(User record);

    @Select("select u.* from public.user u  where u.user_name= #{username}")
    @Results(id = "BaseResultMap",value = {
            @Result(property = "userId",column = "user_id"),
            @Result(property = "userName",column = "user_name"),
            @Result(property = "userPwd",column = "user_pwd"),
            @Result(property = "version",column = "version"),
            @Result(property = "roles",javaType = List.class,column = "user_id",many = @Many(select = "com.example.demo.dao.UserDao.findRoles"))
    })
    User selectUserByUsername(String username);


    @Select("select r.* from public.user_role sru LEFT OUTER JOIN public.role r on r.role_id = sru.role_id where sru.user_id = #{userId}")
    List<Role> findRoles(Integer userId);
}
