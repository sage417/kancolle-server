package com.kancolle.server.service.battle.formation;

/**
 * Created by J.K.SAGE on 2017/1/12.
 */
public class Formation {

    private double shellMod;

    private double torpMod;

    private double ASWMod;

    private double AAMod;

    private double shellAcc;

    private double torpAcc;

    private double NBAcc;

    private double shellLev;

    private double NBEm;

    private double ASWEv;

    private int id;

    public double getShellMod() {
        return shellMod;
    }

    public void setShellMod(double shellMod) {
        this.shellMod = shellMod;
    }

    public double getTorpMod() {
        return torpMod;
    }

    public void setTorpMod(double torpMod) {
        this.torpMod = torpMod;
    }

    public double getASWMod() {
        return ASWMod;
    }

    public void setASWMod(double ASWMod) {
        this.ASWMod = ASWMod;
    }

    public double getAAMod() {
        return AAMod;
    }

    public void setAAMod(double AAMod) {
        this.AAMod = AAMod;
    }

    public double getShellAcc() {
        return shellAcc;
    }

    public void setShellAcc(double shellAcc) {
        this.shellAcc = shellAcc;
    }

    public double getTorpAcc() {
        return torpAcc;
    }

    public void setTorpAcc(double torpAcc) {
        this.torpAcc = torpAcc;
    }

    public double getNBAcc() {
        return NBAcc;
    }

    public void setNBAcc(double NBAcc) {
        this.NBAcc = NBAcc;
    }

    public double getShellLev() {
        return shellLev;
    }

    public void setShellLev(double shellLev) {
        this.shellLev = shellLev;
    }

    public double getNBEm() {
        return NBEm;
    }

    public void setNBEm(double NBEm) {
        this.NBEm = NBEm;
    }

    public double getASWEv() {
        return ASWEv;
    }

    public void setASWEv(double ASWEv) {
        this.ASWEv = ASWEv;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
