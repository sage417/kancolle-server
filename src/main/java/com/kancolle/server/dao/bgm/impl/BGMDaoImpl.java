/**
 * 
 */
package com.kancolle.server.dao.bgm.impl;

import com.kancolle.server.dao.base.impl.BaseDaoImpl;
import com.kancolle.server.dao.bgm.BGMDao;
import com.kancolle.server.model.po.furniture.BGM;
import org.springframework.stereotype.Repository;

import java.util.List;

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
