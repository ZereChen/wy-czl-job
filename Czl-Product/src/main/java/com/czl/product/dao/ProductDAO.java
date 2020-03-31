package com.czl.product.dao;

import com.czl.entity.product.ProductEntity;
import com.czl.req.product.ProdInsertReq;
import com.czl.req.product.ProdQueryReq;
import com.czl.req.product.ProdQueryReqForLogin;
import com.czl.req.product.ProdUpdateReq;
import com.czl.rsp.Result;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductDAO {
    /**
     * 新增产品
     * @param prodInsertReq
     * @return
     */
    int createProduct(ProdInsertReq prodInsertReq);

    /**
     * 增量更新产品
     * @param prodUpdateReq
     */
    int updateProduct(ProdUpdateReq prodUpdateReq);

    /**
     * 多条件查询产品
     * @param prodQueryReq
     * @return
     */
    List<ProductEntity> findProducts(ProdQueryReq prodQueryReq);

    /**
     * 删除产品
     * @param productId
     * @return
     */
    int deleteProduct(String productId);

    List<ProductEntity> findProductsForBuyer(ProdQueryReqForLogin prodQueryReqForLogin);

    List<ProductEntity> findProductsForBuyerNoBuyed(ProdQueryReqForLogin prodQueryReqForLogin);

    List<ProductEntity> findProductsForSeller(ProdQueryReqForLogin prodQueryReqForLogin);

    ProductEntity findProductDetailForBuyer(ProdQueryReqForLogin prodQueryReqForLogin);
}
