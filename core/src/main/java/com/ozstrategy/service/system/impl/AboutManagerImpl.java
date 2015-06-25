package com.ozstrategy.service.system.impl;

import com.ozstrategy.dao.BaseDao;
import com.ozstrategy.dao.system.AboutDao;
import com.ozstrategy.model.system.About;
import com.ozstrategy.service.impl.BaseManagerImpl;
import com.ozstrategy.service.system.AboutManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* Created by lihao1 on 6/8/15.
*/
@Service("aboutManager")
public class AboutManagerImpl extends BaseManagerImpl<About> implements AboutManager {
    @Autowired
    private AboutDao aboutDao;

    @Override
    public BaseDao<About> baseDao() {
        return aboutDao;
    }
}
