package com.kancolle.server.model.event;

import com.kancolle.server.model.po.member.Member;

/**
 * Package: com.kancolle.server.model.event
 * Author: mac
 * Date: 2016/12/6
 */
public class MemberCreatedEvent {

    private final Member member;

    public MemberCreatedEvent(Member member) {
        this.member = member;
    }

    public long getMemberId() {
        return member.getMemberId();
    }

    public String getToken() {
        return member.getToken();
    }

    public String getNickName() {
        return member.getNickName();
    }

    public String getNickNameId() {
        return member.getNickNameId();
    }

    public int getActiveFlag() {
        return member.getActiveFlag();
    }

    public long getStartTime() {
        return member.getStartTime();
    }

    public int getLevel() {
        return member.getLevel();
    }

    public int getRank() {
        return member.getRank();
    }

    public long getExperience() {
        return member.getExperience();
    }

    public String getFleetName() {
        return member.getFleetName();
    }

    public String getComment() {
        return member.getComment();
    }

    public String getCommentId() {
        return member.getCommentId();
    }

    public int getMaxChara() {
        return member.getMaxChara();
    }

    public int getMaxSlotItem() {
        return member.getMaxSlotItem();
    }

    public int getMaxKagu() {
        return member.getMaxKagu();
    }

    public long getPlayTime() {
        return member.getPlayTime();
    }

    public int getTutorial() {
        return member.getTutorial();
    }

    public int[] getFurniture() {
        return member.getFurniture();
    }

    public int getDeckCount() {
        return member.getDeckCount();
    }

    public int getKdockCount() {
        return member.getKdockCount();
    }

    public int getNdockCount() {
        return member.getNdockCount();
    }

    public int getfCoin() {
        return member.getfCoin();
    }

    public int getAttackWin() {
        return member.getAttackWin();
    }

    public int getAttackLose() {
        return member.getAttackLose();
    }

    public int getMissionCount() {
        return member.getMissionCount();
    }

    public int getMissionSuccess() {
        return member.getMissionSuccess();
    }

    public int getPracticeWin() {
        return member.getPracticeWin();
    }

    public int getPracticeLose() {
        return member.getPracticeLose();
    }

    public int getPracticeChallenged() {
        return member.getPracticeChallenged();
    }

    public int getPracticeChallengedWin() {
        return member.getPracticeChallengedWin();
    }

    public int getFirstFlag() {
        return member.getFirstFlag();
    }

    public int getTutorialProgress() {
        return member.getTutorialProgress();
    }

    public int[] getPvp() {
        return member.getPvp();
    }

    public int getMedals() {
        return member.getMedals();
    }

    public boolean isLargeDock() {
        return member.isLargeDock();
    }

    public int getPortBGMId() {
        return member.getPortBGMId();
    }

    public int getParallelQuestCount() {
        return member.getParallelQuestCount();
    }
}
