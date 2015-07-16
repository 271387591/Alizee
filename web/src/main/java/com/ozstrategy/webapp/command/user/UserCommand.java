package com.ozstrategy.webapp.command.user;
import com.ozstrategy.model.user.User;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
* Created by lihao1 on 2015-06-10.
*/
public class UserCommand implements Serializable {
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
    private String roleName;
    private Double credits;
    private String portraitName;
    private String portraitUrl;
    private String portraitPath;
    private Date birth;
    private String address;
    private String city;
    private String country;
    private String postalCode;
    private String province;
    private List<RoleCommand> roles=new ArrayList<RoleCommand>();
    public UserCommand() {
    }
    public UserCommand(User model) {
        this.enabled=model.getEnabled();
        this.accountExpired=model.getAccountExpired();
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
        this.credits=model.getCredits();
        this.portraitName=model.getPortraitName();
        this.portraitPath=model.getPortraitPath();
        this.portraitUrl=model.getPortraitUrl();
        this.birth=model.getBirth();
        this.address=model.getAddress();
        this.city=model.getCity();
        this.country=model.getCountry();
        this.postalCode=model.getPostalCode();
        this.province= model.getProvince();
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

    public Double getCredits() {
        return credits;
    }

    public void setCredits(Double credits) {
        this.credits = credits;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getPortraitName() {
        return portraitName;
    }

    public void setPortraitName(String portraitName) {
        this.portraitName = portraitName;
    }

    public String getPortraitUrl() {
        return portraitUrl;
    }

    public void setPortraitUrl(String portraitUrl) {
        this.portraitUrl = portraitUrl;
    }

    public String getPortraitPath() {
        return portraitPath;
    }

    public void setPortraitPath(String portraitPath) {
        this.portraitPath = portraitPath;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }
}
