package com.ozstrategy.webapp.command.appstore;
import com.ozstrategy.model.appstore.AppStore;

import java.util.Date;

/**
* Created by lihao1 on 2015-08-30.
*/
public class AppStoreCommand {
    private Long id;
    private Boolean enabled;
    private Integer plat;
    private String pacUrl;
    private String pacPath;
    private String pacName;
    private Date createDate;
    private String iosUrl;
    private String version;
    public AppStoreCommand() {
    }
    public AppStoreCommand(AppStore model) {
        this.id=model.getId();
        this.enabled=model.getEnabled();
        this.plat=model.getPlat();
        this.pacUrl=model.getPacUrl();
        this.pacPath=model.getPacPath();
        this.pacName=model.getPacName();
        this.createDate=model.getCreateDate();
        this.iosUrl=model.getIosUrl();
        this.version=model.getVersion();
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
    public Integer getPlat() {
        return plat;
    }
    public void setPlat(Integer plat) {
        this.plat = plat;
    }
    public String getPacUrl() {
        return pacUrl;
    }
    public void setPacUrl(String pacUrl) {
        this.pacUrl = pacUrl;
    }
    public String getPacPath() {
        return pacPath;
    }
    public void setPacPath(String pacPath) {
        this.pacPath = pacPath;
    }
    public String getPacName() {
        return pacName;
    }
    public void setPacName(String pacName) {
        this.pacName = pacName;
    }
    public Date getCreateDate() {
        return createDate;
    }
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    public String getIosUrl() {
        return iosUrl;
    }
    public void setIosUrl(String iosUrl) {
        this.iosUrl = iosUrl;
    }
    public String getVersion() {
        return version;
    }
    public void setVersion(String version) {
        this.version = version;
    }
}
