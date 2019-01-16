package com.soldier.service.Impl;

import com.github.pagehelper.PageHelper;
import com.soldier.dao.PermissionDao;
import com.soldier.domain.Permission;
import com.soldier.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    private PermissionDao permissionDao;
    @Override
    public List<Permission> findAll(Integer thisPage,Integer pageSize) {
        PageHelper.startPage(thisPage,pageSize);
        List<Permission> permissions = permissionDao.findAll();
        return permissions;
    }

    @Override
    public String add(Permission permission) {
        permissionDao.add(permission);
        String pid = permission.getId();
        return pid;
    }

    @Override
    public List<Permission> findByRole(String rid) {
        List<Permission> permissions = permissionDao.findByRole(rid);
        return permissions;
    }


}
