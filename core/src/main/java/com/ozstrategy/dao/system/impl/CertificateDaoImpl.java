package com.ozstrategy.dao.system.impl;

import com.ozstrategy.dao.impl.BaseDaoImpl;
import com.ozstrategy.dao.system.CertificateDao;
import com.ozstrategy.model.system.Certificate;
import org.springframework.stereotype.Repository;


/**
* Created by lihao1 on 2015-07-29.
*/
@Repository("certificateDao")
public class CertificateDaoImpl extends BaseDaoImpl<Certificate> implements CertificateDao{

    public CertificateDaoImpl() {
    super(Certificate.class);
    }

}