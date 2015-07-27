/**
 * 
 */
package com.kancolle.server.dao.bgm;

import java.util.List;

import com.kancolle.server.dao.base.BaseDao;
import com.kancolle.server.model.po.furniture.BGM;

/**
 * @author J.K.SAGE
 * @Date 2015年7月27日
 *
 */
public interface BGMDao extends BaseDao<BGM> {

    List<BGM> selectBGMs();

}
