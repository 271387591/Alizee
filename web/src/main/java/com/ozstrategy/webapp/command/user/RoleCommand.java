package com.ozstrategy.webapp.command.user;
import com.ozstrategy.model.user.Role;
/**
* Created by lihao1 on 2015-06-08.
*/
public class RoleCommand {
    private Long id;
    private String name;
    public RoleCommand() {
    }
    public RoleCommand(Role model) {
        this.id=model.getId();
        this.name=model.getName();
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
