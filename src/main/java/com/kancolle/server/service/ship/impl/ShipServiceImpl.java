package com.kancolle.server.service.ship.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.google.common.collect.Lists;
import com.kancolle.server.dao.ship.ShipDao;
import com.kancolle.server.model.kcsapi.member.MemberShip;
import com.kancolle.server.model.po.ship.Ship;
import com.kancolle.server.service.ship.ShipService;

@Service
public class ShipServiceImpl implements ShipService {
    @Autowired
    private ShipDao shipDao;

    @Override
    public MemberShip getMemberShip(String member_id, long ship_id) {
        MemberShip memberShip = shipDao.getMemberShip(member_id, ship_id);
        Ship ship = this.getShipById(memberShip.getApi_ship_id());
        // 火力
        int min_houg = ship.getHoug().getMinValue();
        int now_houg = memberShip.getApi_karyoku().getIntValue(0);
        // 雷装
        int min_raig = ship.getRaig().getMinValue();
        int now_raig = memberShip.getApi_raisou().getIntValue(0);
        // 对空
        int min_tyku = ship.getTyku().getMinValue();
        int now_tyku = memberShip.getApi_taiku().getIntValue(0);
        // 装甲
        int min_souk = ship.getSouk().getMinValue();
        int now_souk = memberShip.getApi_soukou().getIntValue(0);
        // 幸运
        int min_luck = ship.getLuck().getMinValue();
        int now_luck = memberShip.getApi_lucky().getIntValue(0);

        JSONArray api_kyouka = new JSONArray(Lists.newArrayList(now_houg - min_houg, now_raig - min_raig, now_tyku - min_tyku, now_souk - min_souk, now_luck - min_luck));
        memberShip.setApi_kyouka(api_kyouka);
        return memberShip;
    }

    @Cacheable(value = "ship", key = "#ship_id")
    @Override
    public Ship getShipById(int ship_id) {
        return shipDao.getShipById(ship_id);
    }
}
