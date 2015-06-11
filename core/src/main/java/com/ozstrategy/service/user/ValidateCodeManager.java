package com.ozstrategy.service.user;

import com.ozstrategy.model.user.ValidateCode;
import com.ozstrategy.service.BaseManager;

/**
* Created by lihao1 on 2015-06-10.
*/
public interface ValidateCodeManager extends BaseManager<ValidateCode> {
    boolean sendCode(String mobile);
}
