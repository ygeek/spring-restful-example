package com.ysgh.btr.test1.dao;


import com.ysgh.btr.test1.pojo.Role;
import com.ysgh.btr.test1.pojo.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *数据库对表操作实现类，Mybatis注解实现
 */
@Mapper
@Repository
public interface UserDao {

    @Delete("delete from public.user where user_id=#{userId}")
    int deleteByPrimaryKey(Integer userId);

    @Insert("insert into public.user values(#{userId},#{username},#{password},CURRENT_DATE)")
    int insert(User record);

    @Select("select user_id,user_name,user_pwd,version from public.user where user_id = #{userId}")
    @Results(id = "BaseResultMap1",value = {
            @Result(property = "userId",column = "user_id"),
            @Result(property = "username",column = "user_name"),
            @Result(property = "password",column = "user_pwd"),
            @Result(property = "roles",javaType = List.class,column = "user_id",many = @Many(select = "com.ysgh.btr.test1.dao.UserDao.findRoles"))
    })
    User selectByPrimaryKey(Integer userId);

    @Update("update public.user set user_name=#{username},user_pwd=#{password},version=CURRENT_DATE where user_id =#{userId}")
    int updateByPrimaryKey(User record);

    @Select("select u.* from public.user u  where u.user_name= #{username}")
    @Results(id = "BaseResultMap",value = {
            @Result(property = "userId",column = "user_id"),
            @Result(property = "username",column = "user_name"),
            @Result(property = "password",column = "user_pwd"),
            @Result(property = "roles",javaType = List.class,column = "user_id",many = @Many(select = "com.ysgh.btr.test1.dao.UserDao.findRoles"))
    })
    User selectUserByUsername(String username);


    @Select("select r.* from public.user_role sru LEFT OUTER JOIN public.role r on r.role_id = sru.role_id where sru.user_id = #{userId}")
    @Results(id = "RoleResultMap", value={
            @Result(property = "roleid",column = "role_id"),
            @Result(property = "rolename",column = "role_name")
    })
    List<Role> findRoles(Integer userId);
}
