/**
 * 
 */
package com.kancolle.server.dao.bgm.impl;

import com.kancolle.server.dao.base.impl.BaseDaoImpl;
import com.kancolle.server.dao.bgm.FurnitureBGMDao;
import com.kancolle.server.model.po.furniture.FurnitureBGM;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author J.K.SAGE
 * @Date 2015年7月27日
 *
 */
@Repository
public class FurnitureBGMDaoImpl extends BaseDaoImpl<FurnitureBGM> implements FurnitureBGMDao {

    @Override
    public List<FurnitureBGM> selectFurnitureBGMs() {
        return getSqlSession().selectList("selectFurnitureBGMByCond");
    }

    @Override
    public FurnitureBGM selectFurnitureBGMByCond(String music_id) {
        return getSqlSession().selectOne("selectFurnitureBGMByCond", music_id);
    }
}
