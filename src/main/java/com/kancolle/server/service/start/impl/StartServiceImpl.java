package com.kancolle.server.service.start.impl;

import org.springframework.stereotype.Service;

import com.kancolle.server.model.kcsapi.start.StartModel;
import com.kancolle.server.service.start.StartService;

@Service
public class StartServiceImpl implements StartService {

    @Override
    public StartModel getStartModel() {
        StartModel model = new StartModel();

        return model;
    }
}
