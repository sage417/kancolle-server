package com.kancolle.server.service.duty.impl;

import com.kancolle.server.dao.duty.DutyDao;
import com.kancolle.server.model.po.duty.Duty;
import com.kancolle.server.service.duty.DutyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DutyServiceImpl implements DutyService {

    @Autowired
    private DutyDao dutyDao;

    @Override
    public Duty getDutyByNo(int duty_no) {
        return dutyDao.selectDutyById(duty_no);
    }
}
