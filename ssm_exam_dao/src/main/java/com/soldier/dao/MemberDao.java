package com.soldier.dao;

import com.soldier.domain.Member;

public interface MemberDao {
    Member findById(String id);
}
