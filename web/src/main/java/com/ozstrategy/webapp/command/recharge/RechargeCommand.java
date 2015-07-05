package com.ozstrategy.webapp.command.recharge;
import com.ozstrategy.model.recharge.Recharge;

import java.util.Date;

/**
* Created by lihao1 on 2015-07-02.
*/
public class RechargeCommand {
    private Long id;
    private Integer status;
    private Long userId;
    private Double money;
    private Double credits;
    private Date createDate;
    private Date lastUpdateDate;
    public RechargeCommand() {
    }
    public RechargeCommand(Recharge model) {
        this.id=model.getId();
        this.status=model.getStatus();
        this.userId=model.getUserId();
        this.money=model.getMoney();
        this.credits=model.getCredits();
        this.createDate=model.getCreateDate();
        this.lastUpdateDate=model.getLastUpdateDate();
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Integer getStatus() {
        return status;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }
    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public Double getMoney() {
        return money;
    }
    public void setMoney(Double money) {
        this.money = money;
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
    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }
    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }
}
