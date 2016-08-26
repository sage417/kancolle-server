/**
 * 
 */
package com.kancolle.server.service.bgm.impl;

import com.kancolle.server.dao.bgm.BGMDao;
import com.kancolle.server.model.po.furniture.BGM;
import com.kancolle.server.service.bgm.BGMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author J.K.SAGE
 * @Date 2015年7月27日
 *
 */
@Service
public class BGMServiceImpl implements BGMService {
    @Autowired
    private BGMDao BGMDao;

    @Override
    public List<BGM> getBGMs() {
        return BGMDao.selectBGMs();
    }

}
