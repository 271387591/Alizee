package com.ozstrategy.dao.commend.impl;

import com.ozstrategy.dao.impl.BaseDaoImpl;
import com.ozstrategy.dao.commend.CommentDao;
import com.ozstrategy.model.commend.Comment;
import org.springframework.stereotype.Repository;


/**
* Created by lihao1 on 2015-06-18.
*/
@Repository("commentDao")
public class CommentDaoImpl extends BaseDaoImpl<Comment> implements CommentDao{

    public CommentDaoImpl() {
    super(Comment.class);
    }

}