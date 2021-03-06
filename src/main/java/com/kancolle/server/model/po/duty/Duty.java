package com.kancolle.server.model.po.duty;

import org.apache.ibatis.type.Alias;

import java.io.Serializable;
import java.util.List;

@Alias("Duty")
public class Duty implements Serializable {

    public static final int OPERATE_TYPE_POWUP = 1;

    private static final long serialVersionUID = 5910310852645358322L;

    /** 資源獎勵 */
    public static final int BONUS_TYPE_RESOURCE = 1;

    /** 舰队开放 */
    public static final int BONUS_TYPE_OPEN_DECK = 2;

    /** 家具箱 */
    public static final int BONUS_TYPE_FURNITUREBOX = 3;

    /** 开放大型建造 */
    public static final int BONUS_TYPE_LARGEBUILD = 4;

    /** 舰娘奖励 */
    public static final int BONUS_TYPE_SHIP = 11;

    /** 装备奖励 */
    public static final int BONUS_TYPE_SLOTITEM = 12;

    /** 物品奖励 */
    public static final int BONUS_TYPE_USEITEM = 13;

    /** 家具奖励 */
    public static final int BONUS_TYPE_FURNITURE = 14;

    /** 机种转换 */
    public static final int BONUS_TYPE_MODEL_CHANGE = 15;

    private int no;

    private int category;

    private int type;

    private String title;

    private String detail;

    /** 用于识别哪些任务与之对应 */
    private int operate;

    private int[] material;

    private int bonusFlag;

    private List<DutyBonus> dutyBonus;

    private int invalidFlag;

    private Duty parent;

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public int getOperate() {
        return operate;
    }

    public void setOperate(int operate) {
        this.operate = operate;
    }

    public int[] getMaterial() {
        return material;
    }

    public void setMaterial(int[] material) {
        this.material = material;
    }

    public int getBonusFlag() {
        return bonusFlag;
    }

    public void setBonusFlag(int bonusFlag) {
        this.bonusFlag = bonusFlag;
    }

    public List<DutyBonus> getDutyBonus() {
        return dutyBonus;
    }

    public void setDutyBonus(List<DutyBonus> dutyBonus) {
        this.dutyBonus = dutyBonus;
    }

    public int getInvalidFlag() {
        return invalidFlag;
    }

    public void setInvalidFlag(int invalidFlag) {
        this.invalidFlag = invalidFlag;
    }

    public Duty getParent() {
        return parent;
    }

    public void setParent(Duty parent) {
        this.parent = parent;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + no;
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
        Duty other = (Duty) obj;
        if (no != other.no)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Duty [title=" + title + "]";
    }
}
