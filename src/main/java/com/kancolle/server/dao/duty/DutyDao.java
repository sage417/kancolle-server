package com.kancolle.server.dao.duty;

import com.kancolle.server.dao.base.BaseDao;
import com.kancolle.server.model.po.duty.Duty;

public interface DutyDao extends BaseDao<Duty> {

    Duty selectDutyById(int duty_no);

}
