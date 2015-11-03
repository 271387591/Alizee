package com.ozstrategy.dao.goods.impl;

import com.ozstrategy.dao.impl.BaseDaoImpl;
import com.ozstrategy.dao.goods.GoodsOrderDao;
import com.ozstrategy.model.goods.GoodsOrder;
import org.springframework.stereotype.Repository;


/**
* Created by lihao1 on 2015-08-04.
*/
@Repository("goodsOrderDao")
public class GoodsOrderDaoImpl extends BaseDaoImpl<GoodsOrder> implements GoodsOrderDao{

    public GoodsOrderDaoImpl() {
    super(GoodsOrder.class);
    }

}