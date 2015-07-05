package com.ozstrategy.webapp.command.games;
import com.ozstrategy.model.games.Game;

import java.util.Date;

/**
* Created by lihao1 on 2015-06-28.
*/
public class GameCommand {
    private Integer platform;
    private String picUrl;
    private String gamePath;
    private String type;
    private String gameName;
    private String version;
    private Long id;
    private String picPath;
    private String gameUrl;
    private String description;
    private String name;
    private Date createDate;
    private String picName;
    private Integer popularity;
    private Integer comment;
    private Integer commend;
    public GameCommand() {
    }
    public GameCommand(Game model) {
        this.platform=model.getPlatform();
        this.picUrl=model.getPicUrl();
        this.gamePath=model.getGamePath();
        this.type=model.getType();
        this.gameName=model.getGameName();
        this.version=model.getVersion();
        this.id=model.getId();
        this.picPath=model.getPicPath();
        this.gameUrl=model.getGameUrl();
        this.description=model.getDescription();
        this.name=model.getName();
        this.createDate=model.getCreateDate();
        this.picName=model.getPicName();
        this.popularity=model.getPopularity();
    }
    public Integer getPlatform() {
        return platform;
    }
    public void setPlatform(Integer platform) {
        this.platform = platform;
    }
    public String getPicUrl() {
        return picUrl;
    }
    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }
    public String getGamePath() {
        return gamePath;
    }
    public void setGamePath(String gamePath) {
        this.gamePath = gamePath;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getGameName() {
        return gameName;
    }
    public void setGameName(String gameName) {
        this.gameName = gameName;
    }
    public String getVersion() {
        return version;
    }
    public void setVersion(String version) {
        this.version = version;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getPicPath() {
        return picPath;
    }
    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }
    public String getGameUrl() {
        return gameUrl;
    }
    public void setGameUrl(String gameUrl) {
        this.gameUrl = gameUrl;
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
    public String getPicName() {
        return picName;
    }
    public void setPicName(String picName) {
        this.picName = picName;
    }
    public Integer getPopularity() {
        return popularity;
    }
    public void setPopularity(Integer popularity) {
        this.popularity = popularity;
    }

    public Integer getComment() {
        return comment;
    }

    public void setComment(Integer comment) {
        this.comment = comment;
    }

    public Integer getCommend() {
        return commend;
    }

    public void setCommend(Integer commend) {
        this.commend = commend;
    }
}
