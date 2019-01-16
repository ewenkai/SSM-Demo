package com.soldier.dao;

import com.soldier.domain.Role;
import com.soldier.domain.UserInfo;

import java.util.List;

public interface RoleDao {
    //通过uid查询role并封装permission
    Role findById(String id);
    //添加角色
    void add(Role role);
    //登录校验
    Role findByIdlogin(String id);
    //查询所有role
    List<Role> findAll();
    //通过rid查询role
    Role findByRid(String id);
    //查询用户可以添加的角色
    List<Role> findByUser(String uid);



}
