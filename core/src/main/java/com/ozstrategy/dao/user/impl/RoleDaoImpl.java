package com.ozstrategy.dao.user.impl;

import com.ozstrategy.dao.impl.BaseDaoImpl;
import com.ozstrategy.dao.user.RoleDao;
import com.ozstrategy.model.user.Role;
import org.springframework.stereotype.Repository;


/**
* Created by lihao1 on 2015-06-08.
*/
@Repository("roleDao")
public class RoleDaoImpl extends BaseDaoImpl<Role> implements RoleDao{

    public RoleDaoImpl() {
    super(Role.class);
    }

}