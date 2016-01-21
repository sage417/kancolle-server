package com.kancolle.server.model.po.map;

import org.apache.ibatis.type.Alias;

import java.io.Serializable;

/**
 * Created by J.K.SAGE on 2016/1/21 0021.
 */
@Alias("MapCell")
public class MapCell implements Serializable{

    private int cellId;

    private int mapareaId;

    private int mapinfoId;

    private int no;

    private boolean rashinFlag;

    private int rashinId;

    private int colorNo;

    private int eventId;

    private int eventKind;

    private int next;

    private int commentKind;

    private int production_kind;

    public int getCellId() {
        return cellId;
    }

    public void setCellId(int cellId) {
        this.cellId = cellId;
    }

    public int getMapareaId() {
        return mapareaId;
    }

    public void setMapareaId(int mapareaId) {
        this.mapareaId = mapareaId;
    }

    public int getMapinfoId() {
        return mapinfoId;
    }

    public void setMapinfoId(int mapinfoId) {
        this.mapinfoId = mapinfoId;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public boolean isRashinFlag() {
        return rashinFlag;
    }

    public void setRashinFlag(boolean rashinFlag) {
        this.rashinFlag = rashinFlag;
    }

    public int getRashinId() {
        return rashinId;
    }

    public void setRashinId(int rashinId) {
        this.rashinId = rashinId;
    }

    public int getColorNo() {
        return colorNo;
    }

    public void setColorNo(int colorNo) {
        this.colorNo = colorNo;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public int getEventKind() {
        return eventKind;
    }

    public void setEventKind(int eventKind) {
        this.eventKind = eventKind;
    }

    public int getNext() {
        return next;
    }

    public void setNext(int next) {
        this.next = next;
    }

    public int getCommentKind() {
        return commentKind;
    }

    public void setCommentKind(int commentKind) {
        this.commentKind = commentKind;
    }

    public int getProduction_kind() {
        return production_kind;
    }

    public void setProduction_kind(int production_kind) {
        this.production_kind = production_kind;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MapCell mapCell = (MapCell) o;

        return cellId == mapCell.cellId;

    }

    @Override
    public int hashCode() {
        return cellId;
    }
}
