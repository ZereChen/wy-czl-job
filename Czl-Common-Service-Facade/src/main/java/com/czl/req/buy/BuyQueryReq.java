package com.czl.req.buy;

import com.czl.req.QueryReq;

/**
 * @author zerechen
 * @description 购买情况查询请求
 */
public class BuyQueryReq extends QueryReq {

    /** 购买id */
    private String id;

    /** 产品id */
    private  String productId;
    /** 用户id */
    private  String userId;

    /** 产品状态码 {@link com.czl.enumeration.product.ProdStateEnum} */
    private Integer prodStateCode;

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

    public Integer getProdStateCode() {
        return prodStateCode;
    }

    public void setProdStateCode(Integer prodStateCode) {
        this.prodStateCode = prodStateCode;
    }

    @Override
    public String toString() {
        return "CarQueryReq{" +
                "id='" + id + '\'' +
                ", productId='" + productId + '\'' +
                ", userId='" + userId + '\'' +
                ", prodStateCode=" + prodStateCode +
                '}';
    }
}

