package com.soldier.dao;

import org.apache.ibatis.annotations.Param;

public interface RolePermissionDao {
    void add(@Param("pid") String pid, @Param("rid") String rid);
}
