package com.ozstrategy.service.goods;

import com.ozstrategy.model.goods.ThreeGoods;
import com.ozstrategy.model.user.User;
import com.ozstrategy.service.BaseManager;

/**
* Created by lihao1 on 2015-08-31.
*/
public interface ThreeGoodsManager extends BaseManager<ThreeGoods> {
    void sale(ThreeGoods threeGoods,User user);
}
