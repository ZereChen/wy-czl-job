package com.czl.order.component.idempotent;

import com.czl.enumeration.order.OrderStateEnum;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @author zerechen
 * @date 2017/11/10 下午5:01
 * @description
 */
@Component
public class CancelOrderIdempotentComponent extends BaseIdempotencyComponent {
    @Override
    protected void setAllowStateList() {
        this.allowStateList = Arrays.asList(OrderStateEnum.INIT, OrderStateEnum.BUYER_UNPAID);
    }
}
