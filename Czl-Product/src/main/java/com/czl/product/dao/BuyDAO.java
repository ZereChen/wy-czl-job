package com.czl.product.dao;

import com.czl.entity.buy.BuyEntity;
import com.czl.req.buy.BuyInsertReq;
import com.czl.req.buy.BuyQueryReq;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface BuyDAO {
    /**
     * 购买
     * @param buyInsertReq
     * @return
     */
    int createBuy(BuyInsertReq buyInsertReq);

    /**
     * 多条件查询购已买产品
     * @param buyQueryReq
     * @return
     */
    List<BuyEntity> findBuys(BuyQueryReq buyQueryReq);

}
