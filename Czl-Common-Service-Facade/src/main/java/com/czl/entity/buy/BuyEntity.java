package com.czl.entity.buy;

import com.czl.entity.product.ProductEntity;
import com.czl.entity.user.UserEntity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author zerechen
 * 购买表实体类
 */
public class BuyEntity implements Serializable {

    /** 购物车ID（主键） */
    private String id;

    /** 产品 */
    private ProductEntity productEntity;

    /** 用户 */
    private UserEntity userEntity;

    /** 数量 */
    private Integer num;

    /** 时间 */
    private Date time;

    /** 价格 */
    private String price;


    public ProductEntity getProductEntity() {
        return productEntity;
    }

    public void setProductEntity(ProductEntity productEntity) {
        this.productEntity = productEntity;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "BuyEntity{" +
                "id='" + id + '\'' +
                ", productEntity=" + productEntity +
                ", userEntity=" + userEntity +
                ", num=" + num +
                ", time=" + time +
                ", price='" + price + '\'' +
                '}';
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

}
