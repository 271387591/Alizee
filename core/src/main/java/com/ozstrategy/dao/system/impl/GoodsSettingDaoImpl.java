package com.ozstrategy.dao.system.impl;

import com.ozstrategy.dao.impl.BaseDaoImpl;
import com.ozstrategy.dao.system.GoodsSettingDao;
import com.ozstrategy.model.system.GoodsSetting;
import org.springframework.stereotype.Repository;


/**
* Created by lihao1 on 2015-07-29.
*/
@Repository("goodsSettingDao")
public class GoodsSettingDaoImpl extends BaseDaoImpl<GoodsSetting> implements GoodsSettingDao{

    public GoodsSettingDaoImpl() {
    super(GoodsSetting.class);
    }

}