package com.soldier.service;

import com.soldier.domain.Permission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RolePermissionService {

    void add(String pid,String rid);
}
