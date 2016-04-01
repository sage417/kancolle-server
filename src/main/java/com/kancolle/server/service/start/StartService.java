package com.kancolle.server.service.start;

import com.kancolle.server.model.kcsapi.start.StartResult;

public interface StartService {
    StartResult getStartModel() throws Exception;
}
