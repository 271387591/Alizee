package com.ozstrategy.service.goods;

import com.ozstrategy.model.goods.GoodsCertificate;
import com.ozstrategy.service.BaseManager;

/**
* Created by lihao1 on 2015-07-29.
*/
public interface GoodsCertificateManager extends BaseManager<GoodsCertificate> {
    void certPay(GoodsCertificate goodsCertificate);
}
