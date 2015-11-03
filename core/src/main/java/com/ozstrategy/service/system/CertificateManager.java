package com.ozstrategy.service.system;

import com.ozstrategy.model.system.Certificate;
import com.ozstrategy.service.BaseManager;

/**
* Created by lihao1 on 2015-07-29.
*/
public interface CertificateManager extends BaseManager<Certificate> {
    void  saveCer(Certificate certificate);
}
