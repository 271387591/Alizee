package com.ozstrategy.model.appstore;

import com.ozstrategy.annotations.Id;
import com.ozstrategy.annotations.Table;
import com.ozstrategy.model.BaseEntity;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import java.util.Date;

/**
 * Created by lihao1 on 7/11/15.
 */
@Table(name = "t_appstore")
public class AppStore extends BaseEntity {
    @Id
    private Long id;
    private String version;
    private String pacName;
    private String pacPath;
    private String pacUrl;
    private Date createDate;
    private Integer plat;
    private Boolean enabled;
    private String iosUrl;

    public AppStore() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getPacName() {
        return pacName;
    }

    public void setPacName(String pacName) {
        this.pacName = pacName;
    }

    public String getPacPath() {
        return pacPath;
    }

    public void setPacPath(String pacPath) {
        this.pacPath = pacPath;
    }

    public String getPacUrl() {
        return pacUrl;
    }

    public void setPacUrl(String pacUrl) {
        this.pacUrl = pacUrl;
    }


    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getPlat() {
        return plat;
    }

    public void setPlat(Integer plat) {
        this.plat = plat;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getIosUrl() {
        return iosUrl;
    }

    public void setIosUrl(String iosUrl) {
        this.iosUrl = iosUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        AppStore user = (AppStore) o;
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
