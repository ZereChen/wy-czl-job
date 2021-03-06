package com.czl.entity.product;

import com.czl.entity.user.UserEntity;
import com.czl.enumeration.product.ProdStateEnum;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author zerechen
 * 产品实体类
 */
public class ProductEntity implements Serializable {

    /** 产品ID（主键） */
    private String id;

    /** 产品名称 */
    private String name;

    /** 产品图片 */
    private String url;

    /** 价格 */
    private String price;

    /** 是否上架 {@link com.czl.enumeration.product.ProdStateEnum} */
    private ProdStateEnum prodStateEnum;

    /** 正文 */
    private String content;

    /** 摘要 */
    private String summary;

    /** 已购买 */
    private boolean buyed;

    /** 已购买/出售的数量 */
    private Integer num;

    private Date time;

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public ProdStateEnum getProdStateEnum() {
        return prodStateEnum;
    }

    public void setProdStateEnum(ProdStateEnum prodStateEnum) {
        this.prodStateEnum = prodStateEnum;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public boolean isBuyed() {
        return buyed;
    }

    public void setBuyed(boolean buyed) {
        this.buyed = buyed;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "ProductEntity{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", price='" + price + '\'' +
                ", prodStateEnum=" + prodStateEnum +
                ", content='" + content + '\'' +
                ", summary='" + summary + '\'' +
                ", buyed=" + buyed +
                ", num=" + num +
                ", time='" + time + '\'' +
                '}';
    }
}
