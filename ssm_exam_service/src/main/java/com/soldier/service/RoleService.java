package com.soldier.service;

import com.soldier.domain.Role;

import java.util.List;

public interface RoleService {
    /*String findIdByName(String roleName);*/
    String add(Role role);
    List<Role> findAll(Integer thisPage,Integer pageSize);
    List<Role> findByUser(String uid);
    Role findByRid(String id);
}
