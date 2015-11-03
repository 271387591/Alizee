package com.ozstrategy.service.goods.impl;

import com.ozstrategy.dao.BaseDao;
import com.ozstrategy.dao.goods.GoodsCertificateDao;
import com.ozstrategy.dao.goods.MerchantDao;
import com.ozstrategy.dao.user.UserDao;
import com.ozstrategy.model.goods.GoodsCertificate;
import com.ozstrategy.model.goods.Merchant;
import com.ozstrategy.model.user.User;
import com.ozstrategy.service.impl.BaseManagerImpl;
import com.ozstrategy.service.goods.GoodsCertificateManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
* Created by lihao1 on 6/8/15.
*/
@Service("goodsCertificateManager")
public class GoodsCertificateManagerImpl extends BaseManagerImpl<GoodsCertificate> implements GoodsCertificateManager {
    @Autowired
    private GoodsCertificateDao goodsCertificateDao;
    @Autowired
    private MerchantDao merchantDao;
    @Autowired
    private UserDao userDao;



    @Override
    public BaseDao<GoodsCertificate> baseDao() {
        return goodsCertificateDao;
    }

    public void certPay(GoodsCertificate goodsCertificate) {
        Merchant merchant=merchantDao.get(goodsCertificate.getMerchantId());
        merchant.setCredits(merchant.getCredits() + goodsCertificate.getCredits());
        merchantDao.update(merchant);
        goodsCertificate.setEnabled(false);
        goodsCertificate.setLastUpdate(new Date());
        goodsCertificateDao.update(goodsCertificate);
        User user=userDao.get(merchant.getUserId());
        user.setCredits(merchant.getCredits());
        userDao.update(user);
    }
}
