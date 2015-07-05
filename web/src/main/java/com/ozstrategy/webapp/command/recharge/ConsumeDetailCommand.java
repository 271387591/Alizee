package com.ozstrategy.webapp.command.recharge;
import com.ozstrategy.model.recharge.ConsumeDetail;

import java.util.Date;

/**
* Created by lihao1 on 2015-07-02.
*/
public class ConsumeDetailCommand {
    private Long id;
    private Long userId;
    private Double credits;
    private Date createDate;
    private Integer type;
    public ConsumeDetailCommand() {
    }
    public ConsumeDetailCommand(ConsumeDetail model) {
        this.id=model.getId();
        this.userId=model.getUserId();
        this.credits=model.getCredits();
        this.createDate=model.getCreateDate();
        this.type=model.getType();
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
    public Double getCredits() {
        return credits;
    }
    public void setCredits(Double credits) {
        this.credits = credits;
    }
    public Date getCreateDate() {
        return createDate;
    }
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    public Integer getType() {
        return type;
    }
    public void setType(Integer type) {
        this.type = type;
    }
}
