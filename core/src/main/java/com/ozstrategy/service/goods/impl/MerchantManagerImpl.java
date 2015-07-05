package com.ozstrategy.service.goods.impl;

import com.ozstrategy.dao.BaseDao;
import com.ozstrategy.dao.goods.MerchantDao;
import com.ozstrategy.model.goods.Merchant;
import com.ozstrategy.service.impl.BaseManagerImpl;
import com.ozstrategy.service.goods.MerchantManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* Created by lihao1 on 6/8/15.
*/
@Service("merchantManager")
public class MerchantManagerImpl extends BaseManagerImpl<Merchant> implements MerchantManager {
    @Autowired
    private MerchantDao merchantDao;

    @Override
    public BaseDao<Merchant> baseDao() {
        return merchantDao;
    }
}
