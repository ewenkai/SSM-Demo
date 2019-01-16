package com.soldier.dao;

import com.soldier.domain.UserInfo;

import java.util.List;

public interface UserDao {
    UserInfo findByName(String username);
    List<UserInfo> findAll();
    void add(UserInfo userInfo);
    UserInfo findById(String id);

}
