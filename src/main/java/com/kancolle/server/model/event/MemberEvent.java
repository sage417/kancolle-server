package com.kancolle.server.model.event;

public class MemberEvent {

    private String member_id;

    public MemberEvent(String member_id) {
        super();
        this.member_id = member_id;
    }

    public String getMember_id() {
        return member_id;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }
}
