package com.kancolle.server.modle.kcsapi.start.sub;

public class ConstModel {

    private InnerModel api_parallel_quest_max;

    private InnerModel api_boko_max_ships;

    private InnerModel api_dpflag_quest;

    public InnerModel getApi_parallel_quest_max() {
        return api_parallel_quest_max;
    }

    public void setApi_parallel_quest_max(InnerModel api_parallel_quest_max) {
        this.api_parallel_quest_max = api_parallel_quest_max;
    }

    public InnerModel getApi_boko_max_ships() {
        return api_boko_max_ships;
    }

    public void setApi_boko_max_ships(InnerModel api_boko_max_ships) {
        this.api_boko_max_ships = api_boko_max_ships;
    }

    public InnerModel getApi_dpflag_quest() {
        return api_dpflag_quest;
    }

    public void setApi_dpflag_quest(InnerModel api_dpflag_quest) {
        this.api_dpflag_quest = api_dpflag_quest;
    }

    @SuppressWarnings("unused")
    private static class InnerModel {
        public String api_string_value;
        public int api_int_value;
    }
}
