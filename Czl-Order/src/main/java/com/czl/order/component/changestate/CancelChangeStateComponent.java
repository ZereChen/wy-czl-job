package com.czl.order.component.changestate;

import com.czl.enumeration.order.OrderStateEnum;
import org.springframework.stereotype.Component;

/**
 * @author zerechen
 * @date 2017/11/10 下午5:08
 * @description
 */
@Component
public class CancelChangeStateComponent extends BaseChangeStateComponent {

    @Override
    public void setTargetOrderState() {
        this.targetOrderState = OrderStateEnum.CANCEL;
    }
}
