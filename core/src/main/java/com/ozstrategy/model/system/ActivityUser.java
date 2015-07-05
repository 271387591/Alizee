package com.ozstrategy.model.system;

import com.ozstrategy.annotations.*;
import com.ozstrategy.model.BaseEntity;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import java.util.Date;

/**
 * Created by lihao1 on 6/25/15.
 */
@Table(name = "t_activityuser")
@NamedQueries({
        @NamedQuery(name = "getUsers",query = "select u.username,u.mobile,u.nickName,u.portraitUrl,a.* from t_activityuser a join t_user u on a.userId=u.id where a.activityId=:activityId"),
        @NamedQuery(name = "getUsersCount",query = "select count(*) from t_activityuser a join t_user u on a.userId=u.id where a.activityId=:activityId")
})
public class ActivityUser extends BaseEntity{
    @Id
    private Long id;
    private Long userId;
    private Long activityId;
    private Date createDate;
    private Date lastUpdateDate;
    private Integer status=ActivityUserStatus.CheckPending.ordinal();

    public ActivityUser() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

        ActivityUser user = (ActivityUser) o;
        return new EqualsBuilder()
                .append(id, user.id)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(id)
                .hashCode();

    }
}
