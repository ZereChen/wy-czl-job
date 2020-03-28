package com.czl.order.component.idempotent;

import com.czl.enumeration.order.OrderStateEnum;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @author zerechen
 * @date 2017/11/13 上午8:39
 *
 * @description 确认发货的幂等性检查
 */
@Component
public class ConfirmDeliveryIdempotentComponent extends BaseIdempotencyComponent {
    @Override
    protected void setAllowStateList() {
        // 订单状态只有为"买家待收货"才允许确认发货
        this.allowStateList = Arrays.asList(OrderStateEnum.BUYER_PAID);
    }
}