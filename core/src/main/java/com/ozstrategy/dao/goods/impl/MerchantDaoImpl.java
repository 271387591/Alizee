package com.ozstrategy.dao.goods.impl;

import com.ozstrategy.dao.impl.BaseDaoImpl;
import com.ozstrategy.dao.goods.MerchantDao;
import com.ozstrategy.model.goods.Merchant;
import org.springframework.stereotype.Repository;


/**
* Created by lihao1 on 2015-06-30.
*/
@Repository("merchantDao")
public class MerchantDaoImpl extends BaseDaoImpl<Merchant> implements MerchantDao{

    public MerchantDaoImpl() {
    super(Merchant.class);
    }

}