package com.czl.entity.product;

import com.czl.enumeration.product.ProdStateEnum;

import java.io.Serializable;

/**
 * @author zerechen
 * 卖家产品实体类
 */
public class ProductForSellerEntity extends ProductEntity {

    private int sellNum;

    public int getSellNum() {
        return sellNum;
    }

    public void setSellNum(int sellNum) {
        this.sellNum = sellNum;
    }

    @Override
    public String toString() {
        return "ProductForSellerEntity{" +
                "sellNum=" + sellNum +
                '}';
    }
}
