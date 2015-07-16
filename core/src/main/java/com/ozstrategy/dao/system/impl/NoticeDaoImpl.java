package com.ozstrategy.dao.system.impl;

import com.ozstrategy.dao.impl.BaseDaoImpl;
import com.ozstrategy.dao.system.NoticeDao;
import com.ozstrategy.model.system.Notice;
import org.springframework.stereotype.Repository;


/**
* Created by lihao1 on 2015-07-14.
*/
@Repository("noticeDao")
public class NoticeDaoImpl extends BaseDaoImpl<Notice> implements NoticeDao{

    public NoticeDaoImpl() {
    super(Notice.class);
    }

}