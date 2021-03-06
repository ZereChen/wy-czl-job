package com.czl.facade.product;

import com.czl.entity.product.ProductEntity;
import com.czl.req.product.*;
import com.czl.rsp.Result;

import java.util.List;

/**
 * @author zerechen
 * @description
 */
public interface ProductService {
    /**
     * 新增产品
     * @param prodInsertReq
     * @return
     */
    Result createProduct(ProdInsertReq prodInsertReq);

    /**
     * 增量更新产品
     * @param prodUpdateReq
     * @return
     */
    Result updateProduct(ProdUpdateReq prodUpdateReq);

    /**
     * 多条件查询所有产品（支持分页）
     * @param prodQueryReq
     * @return
     */
    Result<List<ProductEntity>> findProducts(ProdQueryReq prodQueryReq);

    /**
     * 删除产品
     * @param ProductId
     * @return
     */
    Result deleteProduct(String ProductId);

    /**
     * 买家产品列表
     * @param prodQueryReqForLogin
     * @return
     */
    Result<List<ProductEntity>> findProductsForBuyer(ProdQueryReqForLogin prodQueryReqForLogin);

    /**
     * 卖家产品列表
     * @param prodQueryReqForLogin
     * @return
     */
    Result<List<ProductEntity>> findProductsForSeller(ProdQueryReqForLogin prodQueryReqForLogin);

    /**
     * 买家查询产品详情列表
     * @param prodQueryReqForLogin
     * @return
     */
    Result<List<ProductEntity>> findProductDetailForBuyer(ProdQueryReqForLogin prodQueryReqForLogin);
}
