package com.czl.order.component.pay;

import com.czl.context.OrderProcessContext;
import com.czl.order.component.BaseComponent;
import org.springframework.stereotype.Component;

/**
 * @author zerechen
 * @date 2017/11/9 下午3:30
 * @description
 */
@Component
public class WechatPayComponent extends BaseComponent {
    @Override
    public void handle(OrderProcessContext orderProcessContext) {
        preHandle(orderProcessContext);
        // TODO 微信支付
        afterHandle(orderProcessContext);
    }
}
