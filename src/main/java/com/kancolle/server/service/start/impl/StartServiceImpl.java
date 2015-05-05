package com.kancolle.server.service.start.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
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
    @Cacheable(value = "start", key = "#root.methodName")
    public StartModel getStartModel() throws InstantiationException, IllegalAccessException {
        return DaoUtils.setBean(startDao, new Class<?>[] {}, new Object[] {});
    }
}
