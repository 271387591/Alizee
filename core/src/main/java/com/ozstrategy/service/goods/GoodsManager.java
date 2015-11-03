package com.ozstrategy.service.goods;

import com.ozstrategy.exception.GoodsNotHaveException;
import com.ozstrategy.exception.UserCriditsNotHaveException;
import com.ozstrategy.model.goods.Goods;
import com.ozstrategy.model.user.User;
import com.ozstrategy.service.BaseManager;

import java.util.Map;

/**
* Created by lihao1 on 2015-06-30.
*/
public interface GoodsManager extends BaseManager<Goods> {
    Map<String,Object> purchase(Goods goods,Integer num,User user) throws GoodsNotHaveException,UserCriditsNotHaveException;
}
