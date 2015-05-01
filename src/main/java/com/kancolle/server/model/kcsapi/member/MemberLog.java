package com.kancolle.server.model.kcsapi.member;

public class MemberLog {

    private int api_no;

    private String api_type;

    private String api_state;

    private String api_message;

    public int getApi_no() {
        return api_no;
    }

    public void setApi_no(int api_no) {
        this.api_no = api_no;
    }

    public String getApi_type() {
        return api_type;
    }

    public void setApi_type(String api_type) {
        this.api_type = api_type;
    }

    public String getApi_state() {
        return api_state;
    }

    public void setApi_state(String api_state) {
        this.api_state = api_state;
    }

    public String getApi_message() {
        return api_message;
    }

    public void setApi_message(String api_message) {
        this.api_message = api_message;
    }

}
