package com.ozstrategy.model.goods;

import com.ozstrategy.annotations.Id;
import com.ozstrategy.annotations.NamedQueries;
import com.ozstrategy.annotations.NamedQuery;
import com.ozstrategy.annotations.Table;
import com.ozstrategy.model.BaseEntity;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import java.util.Date;

/**
 * Created by lihao1 on 6/30/15.
 */
@Table(name = "t_goods")
@NamedQueries({
        @NamedQuery(name = "getGoods",query = "select r.*,ur.name as merchantName,ur.description as merchantDes,ur.phone,ur.address from t_goods r join t_merchant ur on r.merchantId=ur.id join t_user us on us.id=r.userId  where r.enabled=1 AND us.enabled=1 "),
        @NamedQuery(name = "getGoodsCount",query = "select count(*) from t_goods r join t_merchant ur on r.merchantId=ur.id join t_user us on us.id=r.userId  where r.enabled=1 AND us.enabled=1 ")
})
public class Goods extends BaseEntity {
    @Id
    private Long id;
    private String name;
    private Double price;
    private Date createDate;
    private String description;
    private Long merchantId;
    private Integer num;
    private Boolean enabled=Boolean.TRUE;
    private Long userId;
    private String picPath;
    private String picName;
    private String url;
    private Date lastUpdateDate;
    private Boolean purchase=Boolean.FALSE;
    private Date fixedDate;
    private Integer trends;
    private String logoPath;
    private String logoUrl;
    private String logoName;

    public Goods() {
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public String getPicPath() {
        return picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }

    public String getPicName() {
        return picName;
    }

    public void setPicName(String picName) {
        this.picName = picName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Boolean getPurchase() {
        return purchase;
    }

    public void setPurchase(Boolean purchase) {
        this.purchase = purchase;
    }

    public Date getFixedDate() {
        return fixedDate;
    }

    public void setFixedDate(Date fixedDate) {
        this.fixedDate = fixedDate;
    }

    public Integer getTrends() {
        return trends;
    }

    public void setTrends(Integer trends) {
        this.trends = trends;
    }

    public String getLogoPath() {
        return logoPath;
    }

    public void setLogoPath(String logoPath) {
        this.logoPath = logoPath;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public String getLogoName() {
        return logoName;
    }

    public void setLogoName(String logoName) {
        this.logoName = logoName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Goods user = (Goods) o;
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
