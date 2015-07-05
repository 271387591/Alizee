package com.ozstrategy.service.recharge.impl;

import com.ozstrategy.dao.BaseDao;
import com.ozstrategy.dao.recharge.ConsumeDetailDao;
import com.ozstrategy.model.recharge.ConsumeDetail;
import com.ozstrategy.service.impl.BaseManagerImpl;
import com.ozstrategy.service.recharge.ConsumeDetailManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* Created by lihao1 on 6/8/15.
*/
@Service("consumeDetailManager")
public class ConsumeDetailManagerImpl extends BaseManagerImpl<ConsumeDetail> implements ConsumeDetailManager {
    @Autowired
    private ConsumeDetailDao consumeDetailDao;

    @Override
    public BaseDao<ConsumeDetail> baseDao() {
        return consumeDetailDao;
    }
}
