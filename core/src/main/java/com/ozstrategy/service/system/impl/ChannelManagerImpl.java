package com.ozstrategy.service.system.impl;

import com.ozstrategy.dao.BaseDao;
import com.ozstrategy.dao.system.ChannelDao;
import com.ozstrategy.model.system.Channel;
import com.ozstrategy.service.impl.BaseManagerImpl;
import com.ozstrategy.service.system.ChannelManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* Created by lihao1 on 6/8/15.
*/
@Service("channelManager")
public class ChannelManagerImpl extends BaseManagerImpl<Channel> implements ChannelManager {
    @Autowired
    private ChannelDao channelDao;

    @Override
    public BaseDao<Channel> baseDao() {
        return channelDao;
    }
}
