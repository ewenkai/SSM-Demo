package com.soldier.dao;

import org.apache.ibatis.annotations.Param;


public interface UserRoleDao {
    void add(@Param("uid") String uid, @Param("rid") String rid);
}
