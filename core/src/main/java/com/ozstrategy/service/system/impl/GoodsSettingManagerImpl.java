package com.ozstrategy.service.system.impl;

import com.ozstrategy.dao.BaseDao;
import com.ozstrategy.dao.system.GoodsSettingDao;
import com.ozstrategy.model.system.GoodsSetting;
import com.ozstrategy.service.impl.BaseManagerImpl;
import com.ozstrategy.service.system.GoodsSettingManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
* Created by lihao1 on 6/8/15.
*/
@Service("goodsSettingManager")
public class GoodsSettingManagerImpl extends BaseManagerImpl<GoodsSetting> implements GoodsSettingManager {
    @Autowired
    private GoodsSettingDao goodsSettingDao;

    @Override
    public BaseDao<GoodsSetting> baseDao() {
        return goodsSettingDao;
    }

    public void saveSetting(List<GoodsSetting> goodsSettings) {
        goodsSettingDao.deleteByParam(new HashMap<String, Object>());
        if(goodsSettings!=null && goodsSettings.size()>0){
            batchSave(goodsSettings);
        }
    }
}
