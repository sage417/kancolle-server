/**
 * 
 */
package com.kancolle.server.service.bgm.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kancolle.server.dao.bgm.BGMDao;
import com.kancolle.server.model.po.furniture.BGM;
import com.kancolle.server.service.bgm.BGMService;

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
