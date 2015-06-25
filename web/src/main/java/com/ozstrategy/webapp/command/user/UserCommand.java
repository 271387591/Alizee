package com.ozstrategy.webapp.command.user;
import com.ozstrategy.model.user.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
* Created by lihao1 on 2015-06-10.
*/
public class UserCommand {
    private Boolean enabled;
    private Boolean accountExpired;
    private String password;
    private Boolean accountLocked;
    private Date lastUpdateDate;
    private Long id;
    private String username;
    private String nickName;
    private Boolean credentialsExpired;
    private String gender;
    private Date createDate;
    private String mobile;
    private Long roleId;
    private List<RoleCommand> roles=new ArrayList<RoleCommand>();
    public UserCommand() {
    }
    public UserCommand(User model) {
        this.enabled=model.getEnabled();
        this.accountExpired=model.getAccountExpired();
        this.password=model.getPassword();
        this.accountLocked=model.getAccountLocked();
        this.lastUpdateDate=model.getLastUpdateDate();
        this.id=model.getId();
        this.username=model.getUsername();
        this.nickName=model.getNickName();
        this.credentialsExpired=model.getCredentialsExpired();
        this.gender=model.getGender();
        this.createDate=model.getCreateDate();
        this.mobile=model.getMobile();
        this.roleId=model.getRoleId();
    }
    public Boolean getEnabled() {
        return enabled;
    }
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
    public Boolean getAccountExpired() {
        return accountExpired;
    }
    public void setAccountExpired(Boolean accountExpired) {
        this.accountExpired = accountExpired;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public Boolean getAccountLocked() {
        return accountLocked;
    }
    public void setAccountLocked(Boolean accountLocked) {
        this.accountLocked = accountLocked;
    }
    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }
    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
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
    public Boolean getCredentialsExpired() {
        return credentialsExpired;
    }
    public void setCredentialsExpired(Boolean credentialsExpired) {
        this.credentialsExpired = credentialsExpired;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public Date getCreateDate() {
        return createDate;
    }
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    public String getMobile() {
        return mobile;
    }
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    public Long getRoleId() {
        return roleId;
    }
    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public List<RoleCommand> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleCommand> roles) {
        this.roles = roles;
    }
}
