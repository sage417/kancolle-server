/**
 * 
 */
package com.kancolle.server.dao.bgm.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.kancolle.server.dao.base.impl.BaseDaoImpl;
import com.kancolle.server.dao.bgm.BGMDao;
import com.kancolle.server.model.po.furniture.BGM;

/**
 * @author J.K.SAGE
 * @Date 2015年7月27日
 *
 */
@Repository
public class BGMDaoImpl extends BaseDaoImpl<BGM> implements BGMDao {

    @Override
    public List<BGM> selectBGMs() {
        return getSqlSession().selectList("selectBGMs");
    }
}
