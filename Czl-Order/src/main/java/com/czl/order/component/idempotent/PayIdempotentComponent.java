package com.czl.order.component.idempotent;

import com.czl.enumeration.order.OrderStateEnum;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @author zerechen
 * @date 2017/11/10 下午4:32
 *
 * @description 支付前的幂等性检查
 */
@Component
public class PayIdempotentComponent extends BaseIdempotencyComponent {
    @Override
    protected void setAllowStateList() {
        // 订单为INIT才允许支付
        this.allowStateList = Arrays.asList(OrderStateEnum.INIT);
    }
}