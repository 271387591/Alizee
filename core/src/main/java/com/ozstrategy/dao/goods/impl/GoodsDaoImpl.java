package com.ozstrategy.dao.goods.impl;

import com.ozstrategy.dao.impl.BaseDaoImpl;
import com.ozstrategy.dao.goods.GoodsDao;
import com.ozstrategy.model.goods.Goods;
import org.springframework.stereotype.Repository;


/**
* Created by lihao1 on 2015-06-30.
*/
@Repository("goodsDao")
public class GoodsDaoImpl extends BaseDaoImpl<Goods> implements GoodsDao{

    public GoodsDaoImpl() {
    super(Goods.class);
    }
}