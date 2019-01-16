package com.soldier.dao;

import com.soldier.domain.Permission;

import java.util.List;

public interface PermissionDao {
    /**
     * 通过Rid查询permission
     * @param rid
     * @return
     */
    Permission findById(String rid);

    /**
     * 查询所有权限
     * @return
     */
    List<Permission> findAll();

    /**
     * 添加权限
     * @param permission
     * @return
     */
    void add(Permission permission);

    /**
     * 查询指定角色的可添加权限
     * @param rid
     * @return
     */
    List<Permission> findByRole(String rid);
}
