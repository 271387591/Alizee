package com.ozstrategy.webapp.command.user;
import com.ozstrategy.model.user.User;
/**
* Created by lihao1 on 2015-06-08.
*/
public class UserCommand {
    private Long id;
    private String username;
    private String name;
    private Long depId;
    private Long roleId;
    public UserCommand() {
    }
    public UserCommand(User model) {
        this.id=model.getId();
        this.username=model.getUsername();
        this.name=model.getName();
        this.depId=model.getDepId();
        this.roleId=model.getRoleId();
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
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Long getDepId() {
        return depId;
    }
    public void setDepId(Long depId) {
        this.depId = depId;
    }
    public Long getRoleId() {
        return roleId;
    }
    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
}
