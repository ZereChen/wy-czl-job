package com.czl.order.dao;

import com.czl.entity.order.OrderStateTimeEntity;
import com.czl.entity.order.OrdersEntity;
import com.czl.entity.order.ProductOrderEntity;
import com.czl.req.order.OrderQueryReq;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author zerechen
 * @date 2017/11/6 下午3:14
 * @description
 */
@Mapper
public interface OrderDAO {

    /**
     * 查询订单
     * @param orderQueryReq 订单查询请求
     * @return 订单查询结果
     */
    List<OrdersEntity> findOrders(@Param("orderQueryReq") OrderQueryReq orderQueryReq);

    /**
     * 更新订单状态
     * @param ordersEntity 订单更新请求
     */
    void updateOrder(@Param("ordersEntity") OrdersEntity ordersEntity);

    /**
     * 删除订单某一状态的时间
     * @param orderStateTimeEntity 订单状态-时间的对应关系
     */
    void deleteOrderStateTime(@Param("orderStateTimeEntity") OrderStateTimeEntity orderStateTimeEntity);

    /**
     * 插入订单某一状态的时间
     * @param orderStateTimeEntity 订单状态-时间的对应关系
     */
    void insertOrderStateTime(@Param("orderStateTimeEntity") OrderStateTimeEntity orderStateTimeEntity);

    /**
     * 插入订单
     * @param ordersEntity
     */
    void insertOrder(@Param("ordersEntity") OrdersEntity ordersEntity);

    /**
     * 插入订单产品
     * @param productOrderEntity
     */
    void insertOrderProduct(@Param("productOrderEntity") ProductOrderEntity productOrderEntity);
}
