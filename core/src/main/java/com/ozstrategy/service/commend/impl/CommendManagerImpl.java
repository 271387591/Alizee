package com.ozstrategy.service.commend.impl;

import com.ozstrategy.dao.BaseDao;
import com.ozstrategy.dao.commend.CommendDao;
import com.ozstrategy.model.commend.Commend;
import com.ozstrategy.service.impl.BaseManagerImpl;
import com.ozstrategy.service.commend.CommendManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* Created by lihao1 on 6/8/15.
*/
@Service("commendManager")
public class CommendManagerImpl extends BaseManagerImpl<Commend> implements CommendManager {
    @Autowired
    private CommendDao commendDao;

    @Override
    public BaseDao<Commend> baseDao() {
        return commendDao;
    }
}
