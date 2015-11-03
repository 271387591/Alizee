package com.ozstrategy.service.system;

import com.ozstrategy.model.system.GoodsSetting;
import com.ozstrategy.service.BaseManager;

import java.util.List;

/**
* Created by lihao1 on 2015-07-29.
*/
public interface GoodsSettingManager extends BaseManager<GoodsSetting> {
    void saveSetting(List<GoodsSetting> goodsSettings);
}
