package com.czl.req.buy;

import com.czl.req.AbsReq;

/**
 * @author zerechen
 * @description 购买请求
 */
public class BuyInsertReq extends AbsReq {
    /** 购物车id */
    private String id;

    /** 产品id */
    private  String productId;
    /** 用户id */
    private  String userId;

    /** 数量 */
    private Integer num;

    /** 时间 */
    private String time;

    /** 价格 */
    private String price;

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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "BuyInsertReq{" +
                "id='" + id + '\'' +
                ", productId='" + productId + '\'' +
                ", userId='" + userId + '\'' +
                ", num=" + num +
                ", time='" + time + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
