package com.soldier.service;

import com.soldier.domain.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    List<UserInfo> findAll(Integer thispage,Integer pageSize);
    String add(UserInfo userInfo);
    UserInfo findById(String id);
}
