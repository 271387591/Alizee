package com.ozstrategy.dao.user.impl;

import com.ozstrategy.dao.impl.BaseDaoImpl;
import com.ozstrategy.dao.user.ValidateCodeDao;
import com.ozstrategy.jdbc.EntityBuilder;
import com.ozstrategy.model.user.ValidateCode;
import org.springframework.stereotype.Repository;


/**
* Created by lihao1 on 2015-06-10.
*/
@Repository("validateCodeDao")
public class ValidateCodeDaoImpl extends BaseDaoImpl<ValidateCode> implements ValidateCodeDao{

    public ValidateCodeDaoImpl() {
    super(ValidateCode.class);
    }


}