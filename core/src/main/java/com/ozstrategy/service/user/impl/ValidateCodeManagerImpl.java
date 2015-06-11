package com.ozstrategy.service.user.impl;

import com.ozstrategy.dao.BaseDao;
import com.ozstrategy.dao.user.ValidateCodeDao;
import com.ozstrategy.model.user.ValidateCode;
import com.ozstrategy.service.impl.BaseManagerImpl;
import com.ozstrategy.service.user.ValidateCodeManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* Created by lihao1 on 6/8/15.
*/
@Service("validateCodeManager")
public class ValidateCodeManagerImpl extends BaseManagerImpl<ValidateCode> implements ValidateCodeManager {
    @Autowired
    private ValidateCodeDao validateCodeDao;

    @Override
    public BaseDao<ValidateCode> baseDao() {
        return validateCodeDao;
    }

    public boolean sendCode(String mobile) {


        return false;
    }
}
