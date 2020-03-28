package com.czl.order.component.changestate;

import com.czl.enumeration.order.OrderStateEnum;
import org.springframework.stereotype.Component;

/**
 * @author zerechen
 * @date 2017/11/13 上午8:55
 * @description
 */
@Component
public class FinishedChangeStateComponent extends BaseChangeStateComponent {
    @Override
    public void setTargetOrderState() {
        this.targetOrderState = OrderStateEnum.FINISHED;
    }
}