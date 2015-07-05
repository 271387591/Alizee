package com.ozstrategy.webapp.command.user;
import com.ozstrategy.model.user.Role;

import java.io.Serializable;
import java.util.Date;

/**
* Created by lihao1 on 2015-06-10.
*/
public class RoleCommand implements Serializable {
    private Long id;
    private Boolean enabled;
    private String description;
    private String name;
    private Date createDate;
    private String displayName;
    private Date lastUpdateDate;
    public RoleCommand() {
    }
    public RoleCommand(Role model) {
        this.id=model.getId();
        this.enabled=model.getEnabled();
        this.description=model.getDescription();
        this.name=model.getName();
        this.createDate=model.getCreateDate();
        this.displayName=model.getDisplayName();
        this.lastUpdateDate=model.getLastUpdateDate();
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Boolean getEnabled() {
        return enabled;
    }
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Date getCreateDate() {
        return createDate;
    }
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    public String getDisplayName() {
        return displayName;
    }
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }
    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }
}
