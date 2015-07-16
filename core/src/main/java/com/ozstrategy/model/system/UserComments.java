package com.ozstrategy.model.system;

import com.ozstrategy.annotations.Id;
import com.ozstrategy.annotations.NamedQueries;
import com.ozstrategy.annotations.NamedQuery;
import com.ozstrategy.annotations.Table;
import com.ozstrategy.model.BaseEntity;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import java.util.Date;

/**
 * Created by lihao1 on 7/14/15.
 */
@Table(name = "t_usercomments")
@NamedQueries({
        @NamedQuery(name = "getComments",query = "select uc.*,u.mobile,u.nickName from t_usercomments uc join t_user u on uc.userId=u.id where 1=1"),
        @NamedQuery(name = "getCommentsCount",query = "select count(*) from t_usercomments uc join t_user u on uc.userId=u.id where 1=1"),
        @NamedQuery(name = "getComment",query = "select * from t_usercomments uc join t_user u on uc.userId=u.id where 1=1 and uc.id=:id"),
})
public class UserComments  extends BaseEntity{
    @Id
    private Long id;
    private Long userId;
    private String content;
    private String contract;
    private Date createDate;
    private String reply;
    private Date lastUpdateDate;

    public UserComments() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContract() {
        return contract;
    }

    public void setContract(String contract) {
        this.contract = contract;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
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

        UserComments user = (UserComments) o;
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
