package com.ozstrategy.webapp.command.system;
import com.ozstrategy.model.system.ActivityUser;

import java.util.Date;

/**
* Created by lihao1 on 2015-06-25.
*/
public class ActivityUserCommand {
    private Long activityId;
    private Long id;
    private String username;
    private String nickName;
    private Long userId;
    private Date createDate;
    public ActivityUserCommand() {
    }
    public ActivityUserCommand(ActivityUser model) {
        this.activityId=model.getActivityId();
        this.id=model.getId();
        this.username=model.getUsername();
        this.nickName=model.getNickName();
        this.userId=model.getUserId();
        this.createDate=model.getCreateDate();
    }
    public Long getActivityId() {
        return activityId;
    }
    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getNickName() {
        return nickName;
    }
    public void setNickName(String nickName) {
        this.nickName = nickName;
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
}
