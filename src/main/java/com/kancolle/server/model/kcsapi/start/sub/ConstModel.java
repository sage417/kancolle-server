package com.kancolle.server.model.kcsapi.start.sub;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;

public class ConstModel {

    @JSONField(deserialize = false)
    private static final String STRING_KEY = "api_string_value";

    @JSONField(deserialize = false)
    private static final String INT_KEY = "api_int_value";

    @JSONField(ordinal = 1)
    private static final JSONObject api_dpflag_quest = new JSONObject(2);

    @JSONField(ordinal = 2)
    private static final JSONObject api_boko_max_ships = new JSONObject(2);

    @JSONField(ordinal = 3)
    private static final JSONObject api_parallel_quest_max = new JSONObject(2);


    @JSONField(deserialize = false)
    private static final ConstModel SINGLON = new ConstModel();

    static {
        api_parallel_quest_max.put(STRING_KEY, "");
        api_parallel_quest_max.put(INT_KEY, 10);
        api_boko_max_ships.put(STRING_KEY, "");
        api_boko_max_ships.put(INT_KEY, 260);
        api_dpflag_quest.put(STRING_KEY, "");
        api_dpflag_quest.put(INT_KEY, 1);
    }

    public static ConstModel getInstance() {
        return SINGLON;
    }

    private ConstModel() {
    }

    public JSONObject getApi_boko_max_ships() {
        return api_boko_max_ships;
    }

    public JSONObject getApi_dpflag_quest() {
        return api_dpflag_quest;
    }

    public JSONObject getApi_parallel_quest_max() {
        return api_parallel_quest_max;
    }
}