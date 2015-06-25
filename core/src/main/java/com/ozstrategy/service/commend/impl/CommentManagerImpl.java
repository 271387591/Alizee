package com.ozstrategy.service.commend.impl;

import com.ozstrategy.dao.BaseDao;
import com.ozstrategy.dao.commend.CommentDao;
import com.ozstrategy.model.commend.Comment;
import com.ozstrategy.service.impl.BaseManagerImpl;
import com.ozstrategy.service.commend.CommentManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* Created by lihao1 on 6/8/15.
*/
@Service("commentManager")
public class CommentManagerImpl extends BaseManagerImpl<Comment> implements CommentManager {
    @Autowired
    private CommentDao commentDao;

    @Override
    public BaseDao<Comment> baseDao() {
        return commentDao;
    }
}
