package com.ozstrategy.webapp.command.commend;
import com.ozstrategy.model.commend.Comment;

import java.util.Date;

/**
* Created by lihao1 on 2015-06-18.
*/
public class CommentCommand {
    private String content;
    private Long id;
    private Integer rank;
    private Long userId;
    private Date createDate;
    private Long itemId;
    private Integer typeId;
    public CommentCommand() {
    }
    public CommentCommand(Comment model) {
        this.content=model.getContent();
        this.id=model.getId();
        this.rank=model.getRank();
        this.userId=model.getUserId();
        this.createDate=model.getCreateDate();
        this.itemId=model.getItemId();
        this.typeId=model.getTypeId();
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Integer getRank() {
        return rank;
    }
    public void setRank(Integer rank) {
        this.rank = rank;
    }
    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public Date getCreateDate() {
        return createDate;
    }
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    public Long getItemId() {
        return itemId;
    }
    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }
    public Integer getTypeId() {
        return typeId;
    }
    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }
}
