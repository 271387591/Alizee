package com.ozstrategy.webapp.command.commend;
import com.ozstrategy.model.commend.Commend;

import java.util.Date;

/**
* Created by lihao1 on 2015-06-18.
*/
public class CommendCommand {
    private Long id;
    private Long userId;
    private Date createDate;
    private Long itemId;
    private Integer typeId;
    public CommendCommand() {
    }
    public CommendCommand(Commend model) {
        this.id=model.getId();
        this.userId=model.getUserId();
        this.createDate=model.getCreateDate();
        this.itemId=model.getItemId();
        this.typeId=model.getTypeId();
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
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
