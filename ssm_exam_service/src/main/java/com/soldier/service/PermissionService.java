package com.soldier.service;

import com.soldier.domain.Permission;

import java.util.List;

public interface PermissionService {
    List<Permission> findAll(Integer thispage,Integer pageSize);
    String add(Permission permission);
    List<Permission> findByRole(String rid);
}
