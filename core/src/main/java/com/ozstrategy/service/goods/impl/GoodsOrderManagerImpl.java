package com.ozstrategy.service.goods.impl;

import com.ozstrategy.dao.BaseDao;
import com.ozstrategy.dao.goods.GoodsOrderDao;
import com.ozstrategy.model.goods.GoodsOrder;
import com.ozstrategy.service.impl.BaseManagerImpl;
import com.ozstrategy.service.goods.GoodsOrderManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* Created by lihao1 on 6/8/15.
*/
@Service("goodsOrderManager")
public class GoodsOrderManagerImpl extends BaseManagerImpl<GoodsOrder> implements GoodsOrderManager {
    @Autowired
    private GoodsOrderDao goodsOrderDao;

    @Override
    public BaseDao<GoodsOrder> baseDao() {
        return goodsOrderDao;
    }
}
