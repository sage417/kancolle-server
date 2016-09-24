package com.kancolle.server.model.po.deckport;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.google.common.collect.ImmutableList;
import org.apache.ibatis.type.Alias;

/**
 * Created by J.K.SAGE on 2016/8/15 0015.
 */
@Alias("PresetDeck")
@JsonPropertyOrder(
        value = {"no", "name", "name", "ship"}
)
public class PresetDeck {

    @JsonIgnore
    private long id;

    @JsonIgnore
    private long member_id;

    @JsonProperty(value = "api_preset_no")
    private int no;

    @JsonProperty(value = "api_name")
    private String name;

    @JsonProperty(value = "api_name_id")
    private String name_id;

    @JsonProperty(value = "api_ship")
    private ImmutableList<Long> ship;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getMember_id() {
        return member_id;
    }

    public void setMember_id(long member_id) {
        this.member_id = member_id;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName_id() {
        return name_id;
    }

    public void setName_id(String name_id) {
        this.name_id = name_id;
    }

    public ImmutableList<Long> getShip() {
        return ship;
    }

    public void setShip(ImmutableList<Long> ship) {
        this.ship = ship;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PresetDeck that = (PresetDeck) o;

        return id == that.id;

    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    @Override
    public String toString() {
        return "PresetDeck{" +
                "member_id=" + member_id +
                ", no=" + no +
                ", name='" + name + '\'' +
                ", ship=" + ship +
                '}';
    }
}
