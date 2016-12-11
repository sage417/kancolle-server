package com.kancolle.server.model.event;

public class MemberEvent {

    private final long member_id;

    public MemberEvent(long member_id) {
        super();
        this.member_id = member_id;
    }

    public long getMember_id() {
        return member_id;
    }
}
