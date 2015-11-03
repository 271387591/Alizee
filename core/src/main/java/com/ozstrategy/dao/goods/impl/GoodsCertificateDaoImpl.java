package com.ozstrategy.dao.goods.impl;

import com.ozstrategy.dao.impl.BaseDaoImpl;
import com.ozstrategy.dao.goods.GoodsCertificateDao;
import com.ozstrategy.model.goods.GoodsCertificate;
import org.springframework.stereotype.Repository;


/**
* Created by lihao1 on 2015-07-29.
*/
@Repository("goodsCertificateDao")
public class GoodsCertificateDaoImpl extends BaseDaoImpl<GoodsCertificate> implements GoodsCertificateDao{

    public GoodsCertificateDaoImpl() {
    super(GoodsCertificate.class);
    }

}