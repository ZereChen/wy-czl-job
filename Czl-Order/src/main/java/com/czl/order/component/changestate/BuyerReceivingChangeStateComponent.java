package com.czl.order.component.changestate;

import com.czl.context.OrderProcessContext;
import com.czl.entity.order.OrdersEntity;
import com.czl.enumeration.order.OrderStateEnum;
import com.czl.order.dao.OrderDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author zerechen
 * @date 2017/11/13 上午8:43
 *
 * @description 买家待收货状态
 */
@Component
public class BuyerReceivingChangeStateComponent extends BaseChangeStateComponent {

    @Autowired
    private OrderDAO orderDAO;

    @Override
    protected void preHandle(OrderProcessContext orderProcessContext) {
        super.preHandle(orderProcessContext);

        // 插入物流单号
        insertExpressNo(orderProcessContext);
    }

    /**
     * 插入物流单号
     * @param orderProcessContext
     */
    private void insertExpressNo(OrderProcessContext orderProcessContext) {

        // 获取物流单号
        String expressNo = (String) orderProcessContext.getOrderProcessReq().getReqData();

        // 获取订单ID
        String orderId = orderProcessContext.getOrderProcessReq().getOrderId();

        // 构造插入请求
        OrdersEntity ordersEntity = new OrdersEntity();
        ordersEntity.setId(orderId);
        ordersEntity.setExpressNo(expressNo);

        // 插入物流单号
        orderDAO.updateOrder(ordersEntity);
    }


    @Override
    public void setTargetOrderState() {
        this.targetOrderState = OrderStateEnum.BUYER_RECEIVING;
    }

}
