package com.kancolle.server.dao.duty.impl;

import com.kancolle.server.dao.base.impl.BaseDaoImpl;
import com.kancolle.server.dao.duty.DutyDao;
import com.kancolle.server.model.po.duty.Duty;
import org.springframework.stereotype.Repository;

@Repository
public class DutyDaoImpl extends BaseDaoImpl<Duty>implements DutyDao {

    @Override
    public void update(Duty t) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Duty selectDutyById(int duty_no) {
        return getSqlSession().selectOne("selectDutyById", duty_no);
    }
}
