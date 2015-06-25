package com.ozstrategy.dao.system.impl;

import com.ozstrategy.dao.impl.BaseDaoImpl;
import com.ozstrategy.dao.system.AdvertDao;
import com.ozstrategy.model.system.Advert;
import org.springframework.stereotype.Repository;


/**
* Created by lihao1 on 2015-06-18.
*/
@Repository("advertDao")
public class AdvertDaoImpl extends BaseDaoImpl<Advert> implements AdvertDao{

    public AdvertDaoImpl() {
    super(Advert.class);
    }

}