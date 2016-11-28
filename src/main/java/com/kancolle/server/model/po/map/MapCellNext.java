package com.kancolle.server.model.po.map;

import org.apache.ibatis.type.Alias;

import java.io.Serializable;

/**
 * Created by J.K.SAGE on 2016/1/21 0021.
 */
@Alias("MapCellNext")
public class MapCellNext implements Serializable{

    private int cellId;

    private int mapareaId;

    private int mapinfoId;

    private int no;

    private int colorNo;

    private int eventId;

    private int eventKind;

    private int next;

    private int bossCellNo;

    private int commentKind;

    private int productionKind;

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

    public int getBossCellNo() {
        return bossCellNo;
    }

    public void setBossCellNo(int bossCellNo) {
        this.bossCellNo = bossCellNo;
    }

    public int getCommentKind() {
        return commentKind;
    }

    public void setCommentKind(int commentKind) {
        this.commentKind = commentKind;
    }

    public int getProductionKind() {
        return productionKind;
    }

    public void setProductionKind(int productionKind) {
        this.productionKind = productionKind;
    }
}
