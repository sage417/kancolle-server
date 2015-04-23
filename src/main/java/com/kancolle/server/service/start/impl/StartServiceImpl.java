package com.kancolle.server.service.start.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kancolle.server.dao.start.StartDao;
import com.kancolle.server.model.kcsapi.start.StartModel;
import com.kancolle.server.service.start.StartService;
import com.kancolle.server.utils.DaoUtils;

@Service
public class StartServiceImpl implements StartService {

    @Autowired
    private StartDao startDao;

    @Override
    public StartModel getStartModel() throws InstantiationException, IllegalAccessException {
        return DaoUtils.setObject(startDao, new Class<?>[] {}, new Object[] {});
    }
}
