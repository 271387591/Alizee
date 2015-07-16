package com.ozstrategy.service.system.impl;

import com.ozstrategy.dao.BaseDao;
import com.ozstrategy.dao.system.NoticeDao;
import com.ozstrategy.model.system.Notice;
import com.ozstrategy.service.impl.BaseManagerImpl;
import com.ozstrategy.service.system.NoticeManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* Created by lihao1 on 6/8/15.
*/
@Service("noticeManager")
public class NoticeManagerImpl extends BaseManagerImpl<Notice> implements NoticeManager {
    @Autowired
    private NoticeDao noticeDao;

    @Override
    public BaseDao<Notice> baseDao() {
        return noticeDao;
    }
}
