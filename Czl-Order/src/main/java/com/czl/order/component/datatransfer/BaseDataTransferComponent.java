package com.czl.order.component.datatransfer;

import com.czl.context.OrderProcessContext;
import com.czl.order.component.BaseComponent;

/**
 * @author zerechen
 * @date 2017/11/13 下午2:33
 *
 * @description 数据转化组件的父类
 */
public abstract class BaseDataTransferComponent extends BaseComponent {
    @Override
    public void handle(OrderProcessContext orderProcessContext) {
        preHandle(orderProcessContext);
        transfer(orderProcessContext);
        afterHandle(orderProcessContext);
    }

    /**
     * 将OrderProcessContext中的数据进行转化
     * @param orderProcessContext 订单受理上下文
     */
    protected abstract void transfer(OrderProcessContext orderProcessContext);
}
