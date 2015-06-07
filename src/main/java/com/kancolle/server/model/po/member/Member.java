/**
 * 
 */
package com.kancolle.server.model.po.member;

import org.apache.ibatis.type.Alias;

/**
 * @author J.K.SAGE
 * @Date 2015年6月7日
 *
 */
@Alias("Member")
public class Member {

    private long memberId;

    private String token;

    private String nickName;

    private String nickNameId;

    private int activeFlag;

    private long startTime;

    private int level;

    private int rank;

    private long experience;

    private String fleetName;

    private String comment;

    private String commentId;

    private int maxChara;

    private int maxSlotItem;

    private int maxKagu;

    private long playTime;

    private int tutorial;

    private int[] furniture;

    private int deckCount;

    private int kdockCount;

    private int ndockCount;

    private int fCoin;

    private int attackWin;

    private int attackLose;

    private int missionCount;

    private int missionSuccess;

    private int practiceWin;

    private int practiceLose;

    private int practiceChallenged;

    private int practiceChallengedWin;

    private int firstFlag;

    private int tutorialProgress;

    private int[] pvp;

    private int medals;

    private int portBGMId;

    private int parallelQuestCount;

    public long getMemberId() {
        return memberId;
    }

    public void setMemberId(long memberId) {
        this.memberId = memberId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getNickNameId() {
        return nickNameId;
    }

    public void setNickNameId(String nickNameId) {
        this.nickNameId = nickNameId;
    }

    public int getActiveFlag() {
        return activeFlag;
    }

    public void setActiveFlag(int activeFlag) {
        this.activeFlag = activeFlag;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public long getExperience() {
        return experience;
    }

    public void setExperience(long experience) {
        this.experience = experience;
    }

    public String getFleetName() {
        return fleetName;
    }

    public void setFleetName(String fleetName) {
        this.fleetName = fleetName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    public int getMaxChara() {
        return maxChara;
    }

    public void setMaxChara(int maxChara) {
        this.maxChara = maxChara;
    }

    public int getMaxSlotItem() {
        return maxSlotItem;
    }

    public void setMaxSlotItem(int maxSlotItem) {
        this.maxSlotItem = maxSlotItem;
    }

    public int getMaxKagu() {
        return maxKagu;
    }

    public void setMaxKagu(int maxKagu) {
        this.maxKagu = maxKagu;
    }

    public long getPlayTime() {
        return playTime;
    }

    public void setPlayTime(long playTime) {
        this.playTime = playTime;
    }

    public int getTutorial() {
        return tutorial;
    }

    public void setTutorial(int tutorial) {
        this.tutorial = tutorial;
    }

    public int[] getFurniture() {
        return furniture;
    }

    public void setFurniture(int[] furniture) {
        this.furniture = furniture;
    }

    public int getDeckCount() {
        return deckCount;
    }

    public void setDeckCount(int deckCount) {
        this.deckCount = deckCount;
    }

    public int getKdockCount() {
        return kdockCount;
    }

    public void setKdockCount(int kdockCount) {
        this.kdockCount = kdockCount;
    }

    public int getNdockCount() {
        return ndockCount;
    }

    public void setNdockCount(int ndockCount) {
        this.ndockCount = ndockCount;
    }

    public int getfCoin() {
        return fCoin;
    }

    public void setfCoin(int fCoin) {
        this.fCoin = fCoin;
    }

    public int getAttackWin() {
        return attackWin;
    }

    public void setAttackWin(int attackWin) {
        this.attackWin = attackWin;
    }

    public int getAttackLose() {
        return attackLose;
    }

    public void setAttackLose(int attackLose) {
        this.attackLose = attackLose;
    }

    public int getMissionCount() {
        return missionCount;
    }

    public void setMissionCount(int missionCount) {
        this.missionCount = missionCount;
    }

    public int getMissionSuccess() {
        return missionSuccess;
    }

    public void setMissionSuccess(int missionSuccess) {
        this.missionSuccess = missionSuccess;
    }

    public int getPracticeWin() {
        return practiceWin;
    }

    public void setPracticeWin(int practiceWin) {
        this.practiceWin = practiceWin;
    }

    public int getPracticeLose() {
        return practiceLose;
    }

    public void setPracticeLose(int practiceLose) {
        this.practiceLose = practiceLose;
    }

    public int getPracticeChallenged() {
        return practiceChallenged;
    }

    public void setPracticeChallenged(int practiceChallenged) {
        this.practiceChallenged = practiceChallenged;
    }

    public int getPracticeChallengedWin() {
        return practiceChallengedWin;
    }

    public void setPracticeChallengedWin(int practiceChallengedWin) {
        this.practiceChallengedWin = practiceChallengedWin;
    }

    public int getFirstFlag() {
        return firstFlag;
    }

    public void setFirstFlag(int firstFlag) {
        this.firstFlag = firstFlag;
    }

    public int getTutorialProgress() {
        return tutorialProgress;
    }

    public void setTutorialProgress(int tutorialProgress) {
        this.tutorialProgress = tutorialProgress;
    }

    public int[] getPvp() {
        return pvp;
    }

    public void setPvp(int[] pvp) {
        this.pvp = pvp;
    }

    public int getMedals() {
        return medals;
    }

    public void setMedals(int medals) {
        this.medals = medals;
    }

    public int getPortBGMId() {
        return portBGMId;
    }

    public void setPortBGMId(int portBGMId) {
        this.portBGMId = portBGMId;
    }

    public int getParallelQuestCount() {
        return parallelQuestCount;
    }

    public void setParallelQuestCount(int parallelQuestCount) {
        this.parallelQuestCount = parallelQuestCount;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (memberId ^ (memberId >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Member other = (Member) obj;
        if (memberId != other.memberId)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return String.format("Member [getMemberId()=%s, getNickName()=%s, getLevel()=%s]", getMemberId(), getNickName(), getLevel());
    }
}
