package com.soldier.service.Impl;

import com.soldier.dao.UserRoleDao;
import com.soldier.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRoleServiceImpl implements UserRoleService {
    @Autowired
    private UserRoleDao userRoleDao;
    @Override
    public void add(String uid,String rid) {
        userRoleDao.add(uid,rid);
    }
}
