package com.czl.order.component.idempotent;

import com.czl.enumeration.order.OrderStateEnum;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @author zerechen
 * @date 2017/11/13 上午8:54
 * @description
 */
@Component
public class ConfirmReceiveIdemopotentComponent extends BaseIdempotencyComponent {
    @Override
    protected void setAllowStateList() {
        // 订单状态只有为"买家待收货" 或 "买家已签收 & 买家未确认收货"才允许确认收货
        this.allowStateList = Arrays.asList(OrderStateEnum.BUYER_RECEIVING,
                OrderStateEnum.BUYER_SIGNED_UNCONFIRMED);
    }
}
