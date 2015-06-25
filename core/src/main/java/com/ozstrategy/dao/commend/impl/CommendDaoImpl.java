package com.ozstrategy.dao.commend.impl;

import com.ozstrategy.dao.impl.BaseDaoImpl;
import com.ozstrategy.dao.commend.CommendDao;
import com.ozstrategy.model.commend.Commend;
import org.springframework.stereotype.Repository;


/**
* Created by lihao1 on 2015-06-18.
*/
@Repository("commendDao")
public class CommendDaoImpl extends BaseDaoImpl<Commend> implements CommendDao{

    public CommendDaoImpl() {
    super(Commend.class);
    }

}