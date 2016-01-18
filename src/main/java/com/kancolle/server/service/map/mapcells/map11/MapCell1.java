/**
 *
 */
package com.kancolle.server.service.map.mapcells.map11;

import com.kancolle.server.model.kcsapi.battle.map.MapStartResult;
import com.kancolle.server.service.map.mapcells.AbstractMapCell;
import com.kancolle.server.service.map.mapcells.IStartMapCell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * @author J.K.SAGE
 * @Date 2015年8月21日
 */
@Component
public class MapCell1 implements IStartMapCell {

    @Autowired
    @Qualifier("mapCell2")
    private AbstractMapCell nextPoint;

    private final MapStartResult mapResult = new MapStartResult(1, 1, 1);

    public MapCell1() {
        mapResult.setApi_rashin_flg(0);
        mapResult.setApi_rashin_id(0);
        mapResult.setApi_color_no(4);
        mapResult.setApi_event_id(4);
        mapResult.setApi_event_kind(1);
        mapResult.setApi_next(2);
        mapResult.setApi_bosscell_no(3);
        mapResult.setApi_bosscomp(1);
    }

    @Override
    public MapStartResult getMapStartResult() {
        return mapResult;
    }

    @Override
    public int getMapCellId() {
        return 1;
    }
}
