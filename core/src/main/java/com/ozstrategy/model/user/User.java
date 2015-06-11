package com.ozstrategy.model.user;

import com.ozstrategy.Constants;
import com.ozstrategy.annotations.*;
import com.ozstrategy.model.BaseEntity;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.*;

/**
 * Created by lihao1 on 5/5/15.
 */
@Table(name="t_user")
@NamedQueries({
        @NamedQuery(name = "getRoles",query = "select r.* from t_role r join t_userrole ur on r.id=ur.roleId where ur.userId=:userId")
})
public class User extends BaseEntity implements UserDetails {
    @Id
    private Long     id;
    private Boolean  accountExpired=Boolean.FALSE;
    private Boolean  accountLocked=Boolean.FALSE;
    private Boolean  credentialsExpired=Boolean.FALSE;
    private Boolean  enabled=Boolean.TRUE;
    private String   password;
    private String   username;
    private String   gender;
    private String   mobile;
    private Long roleId;
    private String nickName;
    private Date createDate;
    private Date lastUpdateDate;

    @Transient
    private Set<Role> roles              = new HashSet<Role>();

    public User() {
    }

    public User(String username) {
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getAccountExpired() {
        return accountExpired;
    }

    public void setAccountExpired(Boolean accountExpired) {
        this.accountExpired = accountExpired;
    }

    public Boolean getAccountLocked() {
        return accountLocked;
    }

    public void setAccountLocked(Boolean accountLocked) {
        this.accountLocked = accountLocked;
    }

    public Boolean getCredentialsExpired() {
        return credentialsExpired;
    }

    public void setCredentialsExpired(Boolean credentialsExpired) {
        this.credentialsExpired = credentialsExpired;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Set<GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new LinkedHashSet<GrantedAuthority>();
        authorities.addAll(roles);

        return authorities;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public boolean isAccountNonExpired() {
        return !getAccountExpired();
    }
    public boolean isAccountNonLocked() {
        return !getAccountLocked();
    }
    public boolean isCredentialsNonExpired() {
        return !getCredentialsExpired();
    }
    public boolean isEnabled() {
        return enabled;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        User user = (User) o;
        return new EqualsBuilder()
                .append(id, user.id)
                .append(username,user.username)
                .append(mobile,user.mobile)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(id)
                .append(username)
                .append(mobile)
                .hashCode();

    }


}
