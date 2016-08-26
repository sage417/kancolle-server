/**
 * 
 */
package com.kancolle.server.service.bgm.impl;

import com.kancolle.server.dao.bgm.FurnitureBGMDao;
import com.kancolle.server.model.kcsapi.member.FurnitureCoinResult;
import com.kancolle.server.model.po.furniture.FurnitureBGM;
import com.kancolle.server.model.po.member.Member;
import com.kancolle.server.service.bgm.FurnitureBGMService;
import com.kancolle.server.service.member.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @author J.K.SAGE
 * @Date 2015年7月27日
 *
 */
@Service
public class FurnitureBGMServiceImpl implements FurnitureBGMService {
    private static final String NULL_BGM_ERR_MSG = "member:%s try to get not exists muisc:%s";

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
        FurnitureBGM bgm = checkNotNull(getFurnitureBGMByCond(music_id), NULL_BGM_ERR_MSG, member_id, music_id);

        int useCoin = bgm.getUseCoin();

        Member basic = memberService.getMember(member_id);

        if (useCoin > 0) {
            int fCoin = basic.getfCoin() - useCoin;

            checkArgument(fCoin < 0, "member:%s has't enough coin to buy play music", member_id);

            basic.setfCoin(fCoin);
            memberService.updateMember(basic);
        }

        return new FurnitureCoinResult(basic.getfCoin());
    }

    @Override
    public void setPortBGM(String member_id, int music_id) {
        FurnitureBGM bgm = checkNotNull(getFurnitureBGMByCond(String.valueOf(music_id)), NULL_BGM_ERR_MSG, member_id, music_id);

        Member basic = memberService.getMember(member_id);
        basic.setPortBGMId(bgm.getBgmId());

        memberService.updateMember(basic);
    }
}
