package com.ozstrategy.service.goods.impl;

import com.ozstrategy.dao.BaseDao;
import com.ozstrategy.dao.goods.ThreeGoodsDao;
import com.ozstrategy.dao.user.UserDao;
import com.ozstrategy.model.goods.ThreeGoods;
import com.ozstrategy.model.user.User;
import com.ozstrategy.service.ThreeGoodsNoInstance;
import com.ozstrategy.service.impl.BaseManagerImpl;
import com.ozstrategy.service.goods.ThreeGoodsManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* Created by lihao1 on 6/8/15.
*/
@Service("threeGoodsManager")
public class ThreeGoodsManagerImpl extends BaseManagerImpl<ThreeGoods> implements ThreeGoodsManager {
    @Autowired
    private ThreeGoodsDao threeGoodsDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private ThreeGoodsNoInstance threeGoodsNoInstance;



    @Override
    public BaseDao<ThreeGoods> baseDao() {
        return threeGoodsDao;
    }

    public void sale(ThreeGoods threeGoods,User user) {
        user.setCredits(user.getCredits()-threeGoods.getNum()*threeGoods.getGoodsPrice());
        userDao.update(user);
        threeGoodsDao.save(threeGoods);
        String no=threeGoodsNoInstance.orderNo(threeGoods);
        threeGoods.setOrderNo(no);
        threeGoodsDao.update(threeGoods);
    }
}
