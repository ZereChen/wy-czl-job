package com.czl.order.component.pay;

import com.czl.context.OrderProcessContext;
import com.czl.order.component.BaseComponent;
import org.springframework.stereotype.Component;

/**
 * @author zerechen
 * @date 2017/11/9 下午3:29
 * @description
 */
@Component
public class AlipayComponent extends BaseComponent {

    @Override
    public void preHandle(OrderProcessContext orderProcessContext) {

    }

    @Override
    public void handle(OrderProcessContext orderProcessContext) {
        // TODO
    }

    @Override
    public void afterHandle(OrderProcessContext orderProcessContext) {

    }
}
