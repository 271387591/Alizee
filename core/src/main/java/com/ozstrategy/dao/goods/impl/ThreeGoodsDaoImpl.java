package com.ozstrategy.dao.goods.impl;

import com.ozstrategy.dao.impl.BaseDaoImpl;
import com.ozstrategy.dao.goods.ThreeGoodsDao;
import com.ozstrategy.model.goods.ThreeGoods;
import org.springframework.stereotype.Repository;


/**
* Created by lihao1 on 2015-08-31.
*/
@Repository("threeGoodsDao")
public class ThreeGoodsDaoImpl extends BaseDaoImpl<ThreeGoods> implements ThreeGoodsDao{

    public ThreeGoodsDaoImpl() {
    super(ThreeGoods.class);
    }

}