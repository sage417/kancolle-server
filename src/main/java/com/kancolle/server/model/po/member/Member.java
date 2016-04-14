/**
 * 
 */
package com.kancolle.server.model.po.member;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;

/**
 * @author J.K.SAGE
 * @Date 2015年6月7日
 *
 */
@Alias("Member")
public class Member implements Serializable{

    private static final long serialVersionUID = -8080060402377336023L;

    @JSONField(ordinal = 1, name = "api_member_id")
    private long memberId;

    @JSONField(serialize = false, deserialize = false)
    private String token;

    @JSONField(ordinal = 2, name = "api_nickname")
    private String nickName;

    @JSONField(ordinal = 3, name = "api_nickname_id")
    private String nickNameId;

    @JSONField(ordinal = 4, name = "api_active_flag")
    private int activeFlag;

    @JSONField(ordinal = 5, name = "api_starttime")
    private long startTime;

    @JSONField(ordinal = 6, name = "api_level")
    private int level;

    @JSONField(ordinal = 7, name = "api_rank")
    private int rank;

    @JSONField(ordinal = 8, name = "api_experience")
    private long experience;

    @JSONField(ordinal = 9, name = "api_fleetname", serialzeFeatures = { SerializerFeature.WriteMapNullValue })
    private String fleetName;

    @JSONField(ordinal = 10, name = "api_comment")
    private String comment;

    @JSONField(ordinal = 11, name = "api_comment_id")
    private String commentId;

    @JSONField(ordinal = 12, name = "api_max_chara")
    private int maxChara;

    @JSONField(ordinal = 13, name = "api_max_slotitem")
    private int maxSlotItem;

    @JSONField(ordinal = 14, name = "api_max_kagu")
    private int maxKagu;

    @JSONField(ordinal = 15, name = "api_playtime")
    private long playTime;

    @JSONField(ordinal = 16, name = "api_tutorial")
    private int tutorial;

    @JSONField(ordinal = 17, name = "api_furniture")
    private int[] furniture;

    @JSONField(ordinal = 18, name = "api_count_deck")
    private int deckCount;

    @JSONField(ordinal = 19, name = "api_count_kdock")
    private int kdockCount;

    @JSONField(ordinal = 20, name = "api_count_ndock")
    private int ndockCount;

    @JSONField(ordinal = 21, name = "api_fcoin")
    private int fCoin;

    @JSONField(ordinal = 22, name = "api_st_win")
    private int attackWin;

    @JSONField(ordinal = 23, name = "api_st_lose")
    private int attackLose;

    @JSONField(ordinal = 24, name = "api_ms_count")
    private int missionCount;

    @JSONField(ordinal = 25, name = "api_ms_success")
    private int missionSuccess;

    @JSONField(ordinal = 26, name = "api_pt_win")
    private int practiceWin;

    @JSONField(ordinal = 27, name = "api_pt_lose")
    private int practiceLose;

    @JSONField(ordinal = 28, name = "api_pt_challenged")
    private int practiceChallenged;

    @JSONField(ordinal = 29, name = "api_pt_challenged_win")
    private int practiceChallengedWin;

    @JSONField(ordinal = 30, name = "api_firstflag")
    private int firstFlag;

    @JSONField(ordinal = 31, name = "api_tutorial_progress")
    private int tutorialProgress;

    @JSONField(ordinal = 32, name = "api_pvp")
    private int[] pvp;

    @JSONField(ordinal = 33, name = "api_medals")
    private int medals;

    @JSONField(serialize = false, deserialize = false)
    private boolean largeDock;

    @JSONField(serialize = false, deserialize = false)
    private int portBGMId;

    @JSONField(serialize = false, deserialize = false)
    private int parallelQuestCount;

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder{

        private final Member member = new Member();

        public Builder token(String token){
            this.member.token = token;
            return this;
        }

        public Builder nickName(String nickName){
            this.member.nickName = nickName;
            return this;
        }

        public Builder fleetName(String fleetName){
            this.member.fleetName = fleetName;
            return this;
        }

        public Builder maxChara(int maxChara){
            this.member.maxChara = maxChara;
            return this;
        }

        public Builder maxSlotItem(int maxSlotItem){
            this.member.maxSlotItem = maxSlotItem;
            return this;
        }

        public Member build(){
            return this.member;
        }
    }

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

    public boolean isLargeDock() {
        return largeDock;
    }

    public void setLargeDock(boolean largeDock) {
        this.largeDock = largeDock;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Member member = (Member) o;

        return memberId == member.memberId;

    }

    @Override
    public int hashCode() {
        return (int) (memberId ^ (memberId >>> 32));
    }

    @Override
    public String toString() {
        return String.format("Member [getMemberId()=%s, getNickName()=%s, getLevel()=%s]", getMemberId(), getNickName(), getLevel());
    }
}
