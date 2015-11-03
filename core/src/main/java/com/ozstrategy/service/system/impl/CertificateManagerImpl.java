package com.ozstrategy.service.system.impl;

import com.ozstrategy.dao.BaseDao;
import com.ozstrategy.dao.system.CertificateDao;
import com.ozstrategy.model.system.Certificate;
import com.ozstrategy.service.impl.BaseManagerImpl;
import com.ozstrategy.service.system.CertificateManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
* Created by lihao1 on 6/8/15.
*/
@Service("certificateManager")
public class CertificateManagerImpl extends BaseManagerImpl<Certificate> implements CertificateManager {
    @Autowired
    private CertificateDao certificateDao;

    @Override
    public BaseDao<Certificate> baseDao() {
        return certificateDao;
    }

    public void saveCer(Certificate certificate) {
        certificateDao.deleteByParam(new HashMap<String, Object>());
        certificateDao.save(certificate);
    }
}
