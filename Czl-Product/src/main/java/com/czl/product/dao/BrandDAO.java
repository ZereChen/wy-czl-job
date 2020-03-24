package com.czl.product.dao;

import com.czl.entity.product.BrandEntity;
import com.czl.req.product.BrandInsertReq;
import com.czl.req.product.BrandQueryReq;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by lihang on 2017/11/5.
 */
@Mapper
public interface BrandDAO {
    /**
     * 新增品牌
     * @param brandInsertReq
     * @return
     */
    int createBrand(BrandInsertReq brandInsertReq);

    /**
     * 增量更新品牌
     * @param brandInsertReq
     */
    int updateBrand(BrandInsertReq brandInsertReq);


    /**
     * 查询所有品牌（支持分页）
     * @param brandQueryReq
     * @return
     */
    List<BrandEntity> findBrands(BrandQueryReq brandQueryReq);
}
