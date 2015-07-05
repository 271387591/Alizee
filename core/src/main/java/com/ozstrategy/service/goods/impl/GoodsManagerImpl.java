package com.ozstrategy.service.goods.impl;

import com.ozstrategy.dao.BaseDao;
import com.ozstrategy.dao.goods.GoodsDao;
import com.ozstrategy.dao.user.UserDao;
import com.ozstrategy.exception.GoodsNotHaveException;
import com.ozstrategy.exception.UserCriditsNotHaveException;
import com.ozstrategy.model.goods.Goods;
import com.ozstrategy.model.user.User;
import com.ozstrategy.service.impl.BaseManagerImpl;
import com.ozstrategy.service.goods.GoodsManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
* Created by lihao1 on 6/8/15.
*/
@Service("goodsManager")
public class GoodsManagerImpl extends BaseManagerImpl<Goods> implements GoodsManager {
    @Autowired
    private GoodsDao goodsDao;
    @Autowired
    private UserDao userDao;


    @Override
    public BaseDao<Goods> baseDao() {
        return goodsDao;
    }

    public void purchase(Long id,User user) throws GoodsNotHaveException,UserCriditsNotHaveException {
        Goods goods=goodsDao.get(id);
        if(goods.getNum()<1){
            throw new GoodsNotHaveException("goods has none");
        }
        if(goods.getPrice()>user.getCredits()){
            throw new UserCriditsNotHaveException("user has no enough credits");
        }
        goods.setNum(goods.getNum()-1);
        goodsDao.update(goods);
        Double cridits=user.getCredits()-goods.getPrice();
        user.setCredits(cridits);
        userDao.update(user);




    }
}
