package com.ozstrategy.webapp.command.system;
import com.ozstrategy.model.system.UserComments;

import java.util.Date;

/**
* Created by lihao1 on 2015-07-14.
*/
public class UserCommentsCommand {
    private String content;
    private Long id;
    private String contract;
    private Long userId;
    private Date createDate;
    private String reply;
    private Date lastUpdateDate;
    public UserCommentsCommand() {
    }
    public UserCommentsCommand(UserComments model) {
        this.content=model.getContent();
        this.id=model.getId();
        this.contract=model.getContract();
        this.userId=model.getUserId();
        this.createDate=model.getCreateDate();
        this.reply=model.getReply();
        this.lastUpdateDate=model.getLastUpdateDate();
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
    public String getContract() {
        return contract;
    }
    public void setContract(String contract) {
        this.contract = contract;
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

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }
}
