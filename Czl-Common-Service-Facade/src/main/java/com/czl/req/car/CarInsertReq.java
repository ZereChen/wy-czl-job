package com.czl.req.car;

import com.czl.req.AbsReq;

/**
 * @author zerechen
 * @description 购物车产品新增请求
 */
public class CarInsertReq extends AbsReq {
    /** 购物车id */
    private String id;

    /** 产品id */
    private  String productId;
    /** 用户id */
    private  String userId;

    /** 数量 */
    private Integer num;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "CarInsertReq{" +
                "id='" + id + '\'' +
                ", productId='" + productId + '\'' +
                ", userId='" + userId + '\'' +
                ", num=" + num +
                '}';
    }
}
