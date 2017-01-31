package com.kancolle.server.model.mongo;

import com.kancolle.server.model.po.deckport.SlimDeckPort;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.*;

import java.util.List;
import java.util.Objects;

/**
 * Created by J.K.SAGE on 2017/1/31.
 */
@Entity("member_battle_fleet")
@Indexes(
        @Index(fields = @Field("memberId"))
)
public class MemberBattleFleet {
    @Id
    private ObjectId id;
    @Property("member_id")
    private long memberId;
    @Property("traveller_no")
    private int travellerNo;
    @Property("map_cell_no")
    private int mapCellNo;
    @Embedded("fleets")
    private List<SlimDeckPort> fleets;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public long getMemberId() {
        return memberId;
    }

    public void setMemberId(long memberId) {
        this.memberId = memberId;
    }

    public int getTravellerNo() {
        return travellerNo;
    }

    public void setTravellerNo(int travellerNo) {
        this.travellerNo = travellerNo;
    }

    public int getMapCellNo() {
        return mapCellNo;
    }

    public void setMapCellNo(int mapCellNo) {
        this.mapCellNo = mapCellNo;
    }

    public List<SlimDeckPort> getFleets() {
        return fleets;
    }

    public void setFleets(List<SlimDeckPort> fleets) {
        this.fleets = fleets;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MemberBattleFleet that = (MemberBattleFleet) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
