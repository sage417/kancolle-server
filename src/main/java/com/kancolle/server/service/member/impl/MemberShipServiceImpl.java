/**
 * 
 */
package com.kancolle.server.service.member.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kancolle.server.dao.ship.ShipDao;
import com.kancolle.server.model.po.ship.MemberShip;
import com.kancolle.server.service.member.MemberShipService;

/**
 * @author J.K.SAGE
 * @Date 2015年6月23日
 *
 */

@Service
public class MemberShipServiceImpl implements MemberShipService {
    @Autowired
    private ShipDao ShipDao;

    @Override
    public List<MemberShip> getMemberShips(String memberId) {
        return ShipDao.selectMemberShips(memberId);
    }
}
