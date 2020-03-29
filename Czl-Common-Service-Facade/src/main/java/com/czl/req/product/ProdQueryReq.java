package com.czl.req.product;

import com.czl.req.QueryReq;

/**
 * @author zerechen
 * @description 产品查询请求
 */
public class ProdQueryReq extends QueryReq {

    /** 产品id */
    private String id;

    /** 产品名称 */
    private String name;


    /** 产品状态码 {@link com.czl.enumeration.product.ProdStateEnum} */
    private Integer prodStateCode;


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

    public Integer getProdStateCode() {
        return prodStateCode;
    }

    public void setProdStateCode(Integer prodStateCode) {
        this.prodStateCode = prodStateCode;
    }

    @Override
    public String toString() {
        return "ProdQueryReq{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", prodStateCode=" + prodStateCode +
                ", page=" + page +
                ", numPerPage=" + numPerPage +
                ", currentPage=" + currentPage +
                '}';
    }
}

