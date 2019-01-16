package com.soldier.service.Impl;

import com.soldier.dao.PermissionDao;
import com.soldier.dao.RolePermissionDao;
import com.soldier.domain.Permission;
import com.soldier.service.RolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RolePermissionServiceImpl implements RolePermissionService {
    @Autowired
    private RolePermissionDao rolePermissionDao;
    @Override
    public void add(String pid, String rid) {
        rolePermissionDao.add(pid,rid);
    }

}