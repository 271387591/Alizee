package com.ozstrategy.dao.system.impl;

import com.ozstrategy.dao.impl.BaseDaoImpl;
import com.ozstrategy.dao.system.ChannelDao;
import com.ozstrategy.model.system.Channel;
import org.springframework.stereotype.Repository;


/**
* Created by lihao1 on 2015-08-20.
*/
@Repository("channelDao")
public class ChannelDaoImpl extends BaseDaoImpl<Channel> implements ChannelDao{

    public ChannelDaoImpl() {
    super(Channel.class);
    }

}