package com.ozstrategy.dao.system.impl;

import com.ozstrategy.dao.impl.BaseDaoImpl;
import com.ozstrategy.dao.system.AboutDao;
import com.ozstrategy.model.system.About;
import org.springframework.stereotype.Repository;


/**
* Created by lihao1 on 2015-06-18.
*/
@Repository("aboutDao")
public class AboutDaoImpl extends BaseDaoImpl<About> implements AboutDao{

    public AboutDaoImpl() {
    super(About.class);
    }

}