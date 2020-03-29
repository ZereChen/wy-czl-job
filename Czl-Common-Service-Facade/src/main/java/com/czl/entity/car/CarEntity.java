package com.czl.entity.car;

import com.czl.entity.product.ProductEntity;
import com.czl.entity.user.UserEntity;
import com.czl.enumeration.product.ProdStateEnum;

import java.io.Serializable;

/**
 * @author zerechen
 * 购物车实体类
 */
public class CarEntity implements Serializable {

    /** 购物车ID（主键） */
    private String id;

    /** 产品 */
    private ProductEntity productEntity;

    /** 用户 */
    private UserEntity userEntity;

    /** 数量 */
    private Integer num;

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
        return "CarEntity{" +
                "id='" + id + '\'' +
                ", productEntity=" + productEntity +
                ", userEntity=" + userEntity +
                ", num=" + num +
                '}';
    }
}
