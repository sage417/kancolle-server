/**
 * 
 */
package com.kancolle.server.service.bgm.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kancolle.server.dao.bgm.FurnitureBGMDao;
import com.kancolle.server.model.kcsapi.member.FurnitureCoinResult;
import com.kancolle.server.model.po.furniture.FurnitureBGM;
import com.kancolle.server.model.po.member.Member;
import com.kancolle.server.service.bgm.FurnitureBGMService;
import com.kancolle.server.service.member.MemberService;

/**
 * @author J.K.SAGE
 * @Date 2015年7月27日
 *
 */
@Service
public class FurnitureBGMServiceImpl implements FurnitureBGMService {

    @Autowired
    private FurnitureBGMDao furnitureBGMDao;

    @Autowired
    private MemberService memberService;

    @Override
    public List<FurnitureBGM> getFurnitureBGMs() {
        return furnitureBGMDao.selectFurnitureBGMs();
    }

    @Override
    public FurnitureBGM getFurnitureBGMByCond(String music_id) {
        return furnitureBGMDao.selectFurnitureBGMByCond(music_id);
    }

    @Override
    public FurnitureCoinResult musicPlay(String member_id, String music_id) {
        FurnitureBGM bgm = getFurnitureBGMByCond(music_id);

        if (bgm == null) {
            throw new IllegalArgumentException();
        }

        int useCoin = bgm.getUseCoin();

        Member basic = memberService.getMember(member_id);

        if (useCoin > 0) {
            int fCoin = basic.getfCoin() - useCoin;

            if (fCoin < 0) {
                throw new IllegalStateException();
            }

            basic.setfCoin(fCoin);
            memberService.updateMember(basic);
        }

        return new FurnitureCoinResult(basic.getfCoin());
    }
}
