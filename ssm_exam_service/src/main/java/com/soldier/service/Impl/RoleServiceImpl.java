package com.soldier.service.Impl;

import com.github.pagehelper.PageHelper;
import com.soldier.dao.RoleDao;
import com.soldier.domain.Role;
import com.soldier.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDao roleDao;
    /*@Override
    public String findIdByName(String roleName) {
        String rid = roleDao.findIdByName(roleName);
        return rid;
    }*/

    @Override
    public String add(Role role) {
        roleDao.add(role);
        String rid = role.getId();
        return rid;
    }

    @Override
    public List<Role> findAll(Integer thisPage,Integer pageSize) {
        PageHelper.startPage(thisPage,pageSize);
        List<Role> roles = roleDao.findAll();
        return roles;
    }

    @Override
    public List<Role> findByUser(String uid) {
        List<Role> roles = roleDao.findByUser(uid);
        return roles;
    }



    @Override
    public Role findByRid(String id) {
        Role role = roleDao.findByRid(id);
        return role;
    }
}
