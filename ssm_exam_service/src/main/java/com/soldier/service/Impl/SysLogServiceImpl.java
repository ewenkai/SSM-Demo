package com.soldier.service.Impl;

import com.soldier.dao.SysLogDao;
import com.soldier.domain.SysLog;
import com.soldier.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysLogServiceImpl implements SysLogService {
    @Autowired
    private SysLogDao sysLogDao;
    @Override
    public void add(SysLog sysLog) {
        sysLogDao.add(sysLog);
    }
}
