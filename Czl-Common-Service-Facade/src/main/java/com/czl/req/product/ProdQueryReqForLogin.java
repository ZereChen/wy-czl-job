package com.czl.req.product;

import com.czl.req.QueryReq;

/**
 * @author zerechen
 * @description 买家和卖家产品请求
 */
public class ProdQueryReqForLogin extends QueryReq {

    /** 买家id */
    String userId;

    /** 商品id */
    String productId;

    /** 查询未购买的*/
    boolean queryNoBuy;

    /** 产品状态码 {@link com.czl.enumeration.product.ProdStateEnum} */
    private Integer prodStateCode;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public boolean isQueryNoBuy() {
        return queryNoBuy;
    }

    public void setQueryNoBuy(boolean queryNoBuy) {
        this.queryNoBuy = queryNoBuy;
    }

    public Integer getProdStateCode() {
        return prodStateCode;
    }

    public void setProdStateCode(Integer prodStateCode) {
        this.prodStateCode = prodStateCode;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    @Override
    public String toString() {
        return "ProdQueryReqForLogin{" +
                "userId='" + userId + '\'' +
                ", productId='" + productId + '\'' +
                ", queryNoBuy=" + queryNoBuy +
                ", prodStateCode=" + prodStateCode +
                '}';
    }
}

