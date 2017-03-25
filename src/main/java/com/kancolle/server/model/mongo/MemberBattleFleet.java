package com.kancolle.server.model.mongo;

import com.google.common.base.MoreObjects;
import com.kancolle.server.model.po.deckport.SlimDeckPort;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.*;

import java.util.List;

/**
 * Created by J.K.SAGE on 2017/1/31.
 */
@Entity(value = "member_battle_fleet", noClassnameStored = true)
@Indexes(
        @Index(fields = @Field("memberId"), options = @IndexOptions(unique = true))
)
public class MemberBattleFleet {
    @Id
    private ObjectId id;
    @Property("member_id")
    private Long memberId;
    @Property("traveller_no")
    private Integer travellerNo;
    @Property("map_cell_no")
    private Integer mapCellNo;
    @Property("map_cell_name")
    private String mapCellName;
    @Embedded("fleets")
    private List<SlimDeckPort> fleets;
    @Embedded("battle_result")
    private BattleResult battleResult;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public Integer getTravellerNo() {
        return travellerNo;
    }

    public void setTravellerNo(Integer travellerNo) {
        this.travellerNo = travellerNo;
    }

    public Integer getMapCellNo() {
        return mapCellNo;
    }

    public void setMapCellNo(Integer mapCellNo) {
        this.mapCellNo = mapCellNo;
    }

    public String getMapCellName() {
        return mapCellName;
    }

    public void setMapCellName(String mapCellName) {
        this.mapCellName = mapCellName;
    }

    public List<SlimDeckPort> getFleets() {
        return fleets;
    }

    public void setFleets(List<SlimDeckPort> fleets) {
        this.fleets = fleets;
    }

    public BattleResult getBattleResult() {
        return battleResult;
    }

    public void setBattleResult(BattleResult battleResult) {
        this.battleResult = battleResult;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("memberId", memberId)
                .add("travellerNo", travellerNo)
                .add("mapCellNo", mapCellNo)
                .add("mapCellName", mapCellName)
                .add("fleets", fleets)
                .add("battleResult", battleResult)
                .toString();
    }
}
